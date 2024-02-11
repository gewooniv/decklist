package com.example.decklist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.decklist.data.Cards
import com.example.decklist.viewmodel.MainViewModel

@Composable
fun CardGrid(cards: List<Cards>) {
    val viewModel: MainViewModel = viewModel()
    val viewstate by viewModel.cardsState

    Box(modifier = Modifier.fillMaxSize()){
        when{
            viewstate.loading ->{
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            viewstate.error != null ->{
                Text(text = "An error occurred.")
            }
            else ->{
                LazyVerticalGrid(
                    GridCells.Fixed(2), modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 80.dp)
                ) {
                    items(cards) { card ->
                        CardItemGrid(cards = card)
                    }
                }
            }
        }
    }
}

@Composable
fun CardItemGrid(cards: Cards) {
    val name = cardNameFormatterShort(cards.value, cards.suit, cards.code)

    Column(
        modifier = Modifier
            .padding(top = 18.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Image(
            painter = rememberAsyncImagePainter(cards.image),
            contentDescription = "Image of " + cards.code,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )
        Text(
            text = name,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 12.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

fun cardNameFormatterShort(cardValue : String, cardSuit : String, cardCode : String): String {
    val name: String = if (cardCode.startsWith("X")) {
        "$cardSuit $cardValue"
    } else {
        "$cardValue OF $cardSuit"
    }

    return name
}