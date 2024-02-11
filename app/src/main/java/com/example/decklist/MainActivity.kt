package com.example.decklist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.decklist.data.TabItem
import com.example.decklist.ui.theme.DecklistTheme
import com.example.decklist.viewmodel.MainViewModel

class MainActivity : ComponentActivity() { //
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) { // onCreate start lifecycle
        super.onCreate(savedInstanceState)
        val tabItems = listOf(
            TabItem(
                title = "Home",
                selectedIcon = Icons.Outlined.Home,
                unselectedIcon = Icons.Filled.Home,
            ),
            TabItem(
                title = "List",
                selectedIcon = Icons.AutoMirrored.Outlined.List,
                unselectedIcon = Icons.AutoMirrored.Filled.ListAlt,
            ),
            TabItem(
                title = "About",
                selectedIcon = Icons.Outlined.Person,
                unselectedIcon = Icons.Filled.Person,
            )
        )
        setContent {
            DecklistTheme {
                // a surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var selectedTabIndex by remember {
                        mutableIntStateOf(0)
                    }
                    val pagerState = rememberPagerState {
                        tabItems.size
                    }
                    LaunchedEffect(selectedTabIndex) {
                        pagerState.animateScrollToPage(selectedTabIndex)
                    }
                    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
                        if(!pagerState.isScrollInProgress) {
                            selectedTabIndex = pagerState.currentPage
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        TabRow(selectedTabIndex = selectedTabIndex) {
                            tabItems.forEachIndexed { index, item ->
                                Tab(
                                    selected = index == selectedTabIndex,
                                    onClick = { selectedTabIndex = index },
                                    text = { Text(text = item.title) },
                                    icon = {
                                        Icon(imageVector = if(index == selectedTabIndex){
                                            item.selectedIcon
                                        } else item.unselectedIcon,
                                            contentDescription = null)
                                    }
                                )
                            }
                        }
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {index ->
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ){
                                val viewModel = viewModel<MainViewModel>()
                                val searchText by viewModel.searchText.collectAsState()
                                val cards by viewModel.cards.collectAsState()
                                val isSearching by viewModel.isSearching.collectAsState()

                                when(tabItems[index].title) {
                                    // TAB CONTENT
                                    "Home" -> {
                                        SearchBar(viewModel = viewModel, searchText = searchText)

                                        if (isSearching) {
                                            Box(modifier = Modifier.fillMaxSize()) {
                                                CircularProgressIndicator(
                                                    modifier = Modifier.align(Alignment.Center)
                                                )
                                            }
                                        } else {
                                            CardGrid(cards = cards)
                                        }
                                    }
                                    // TAB CONTENT
                                    "List" -> {
                                        CardList(cards = cards.sortedByDescending { it.code })
                                    }
                                    // TAB CONTENT
                                    "About" -> {
                                        About()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
