package com.example.decklist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.decklist.ui.theme.DecklistTheme
import com.example.decklist.viewmodel.MainViewModel

class MainActivity : ComponentActivity() { //
    override fun onCreate(savedInstanceState: Bundle?) { // onCreate start lifecycle
        super.onCreate(savedInstanceState)
        setContent {
            DecklistTheme {
                // a surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    val viewModel = viewModel<MainViewModel>()
    val searchText by viewModel.searchText.collectAsState()
    val cards by viewModel.cards.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    TopNavBar(viewModel = viewModel, searchText = searchText)

    if (isSearching) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        CardsSection(cards = cards.sortedBy { it.code })
    }
}
