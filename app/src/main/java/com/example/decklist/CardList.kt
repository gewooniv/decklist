package com.example.decklist

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.decklist.data.Cards
import com.example.decklist.viewmodel.MainViewModel
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable

@Composable
fun CardList(cards: List<Cards>) {
    val viewModel: MainViewModel = viewModel()
    val viewstate by viewModel.cardsState
    val data = remember { mutableStateOf(listOfCardNames(cards).toList()) }
    val state = rememberReorderableLazyListState(onMove = { from, to ->
        data.value = data.value.toMutableList().apply {
            add(to.index, removeAt(from.index))
        }
    })

    Box(modifier = Modifier.fillMaxSize()){
        when{
            viewstate.loading ->{
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            viewstate.error != null ->{
                Text(text = "An error occurred.")
            }
            else ->{
                LazyColumn(
                    state = state.listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp)
                        .reorderable(state)
                        .detectReorderAfterLongPress(state)
                ) {
                    items(data.value, { it }) { item ->
                        ReorderableItem(state, key = item) { isDragging ->
                            val elevation = animateDpAsState(if (isDragging) 16.dp else 0.dp,
                                label = "elevation"
                            )
                            Column(
                                modifier = Modifier
                                    .shadow(elevation.value)
                                    .background(MaterialTheme.colorScheme.surface)
                            ) {
                                CardListItem(item = item)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CardListItem(item : String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(6.dp)
    ) {
        Text(item,
            color = Color.Black,
            style = TextStyle(fontSize = 18.sp),
        )
    }
}

fun listOfCardNames(cards: List<Cards>): MutableList<String> {
    val listOfCardNames = mutableListOf<String>()
    for (card in cards) {
        val name: String = cardNameFormatter(card.value, card.suit, card.code)
        listOfCardNames.add(name)
    }

    return listOfCardNames
}

fun cardNameFormatter(cardValue: String, cardSuit: String, cardCode: String): String {
    var value = cardValue
    when (cardValue) {
        "0" -> value = "Zero"
        "1" -> value = "One"
        "2" -> value = "Two"
        "3" -> value = "Three"
        "4" -> value = "Four"
        "5" -> value = "Five"
        "6" -> value = "Six"
        "7" -> value = "Seven"
        "8" -> value = "Eight"
        "9" -> value = "Nine"
        "10" -> value = "Ten"
    }

    val name: String = if (cardCode.startsWith("X")) {
        "$cardSuit $value".lowercase().replaceFirstChar { it.uppercase() }
    } else {
        "$value OF $cardSuit".lowercase().replaceFirstChar { it.uppercase() }
    }

    return name
}
