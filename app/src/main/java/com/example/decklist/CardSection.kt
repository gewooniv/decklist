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

//@Composable
//fun CardSection(modifier: Modifier = Modifier){
//    val cardViewModel: MainViewModel = viewModel()
//    val viewstate by cardViewModel.cardState
//
//    Box(modifier = Modifier.fillMaxSize()){
//        when{
//            viewstate.loading ->{
//                CircularProgressIndicator(modifier.align(Alignment.Center))
//            }
//            viewstate.error != null ->{
//                Text(text = "An error occurred")
//            }
//            else ->{
//                CardsScreen(cards = viewstate.list.shuffled())
//            }
//        }
//    }
//}

@Composable
fun CardsSection(cards: List<Cards>) {
    LazyVerticalGrid(
        GridCells.Fixed(2), modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp)
    ) {
        items(cards) { card ->
            CardItem(cards = card)
        }
    }
}

@Composable
fun CardItem(cards: Cards) {
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

        // format card-names
        val suit = cards.suit
        val value = cards.value

        val name: String = if (cards.code.startsWith("X")) {
            "$suit $value"
        } else {
            "$value OF $suit"
        }

        Text(
            text = name,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 12.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
