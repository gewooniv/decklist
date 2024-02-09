package com.example.decklist.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.decklist.data.Cards
import com.example.decklist.services.cardService
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class MainViewModel : ViewModel() {

    // these states are used to reload the UI when the card data changes
    private val _cardState = mutableStateOf(CardState())
    val cardsState: State<CardState> = _cardState

    // search states
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false )
    val isSearching = _isSearching.asStateFlow()

    private val _cards = MutableStateFlow(CardState().list)

    val cards = searchText
        .onEach { _isSearching.update{ true } }
        .debounce(500) // add delay before other blocks are executed
        .combine(_cards) { text, cards ->
            if(text.isBlank()){
                cards
            } else {
                cards.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update{ false }  }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _cards.value
        )

    // search
    fun onSearchTextChange(text: String){
        _searchText.value = text
    }

    init {
        fetchCards()
    }

    private fun fetchCards(){
        // provides launch for suspend functions
        viewModelScope.launch {
            try {

//                // CardState
//                val response = cardService.getCards() // call suspend function
//                _cardState.value = _cardState.value.copy(
//                    list = response.cards,
//                    loading = false,
//                    error = null
//                )

                // CardState.list
                val response = cardService.getCards() // call suspend function
                _cards.value = response.cards

                _cards.value += jokers // manually add jokers

            } catch (e: Exception){
                // on error
                _cardState.value = _cardState.value.copy(
                    loading = false,
                    error = "Error fetching Cards ${e.message}"
                )
            }
        }
    }

    data class CardState(
        val loading: Boolean = true,
        var list: List<Cards> = emptyList(),
        val error: String? = null
        )
}

val jokers = listOf(
    Cards(
        "X1",
        "https://deckofcardsapi.com/static/img/X1.png",
        null,
        "JOKER",
        "BLACK"),
    Cards(
        "X2",
        "https://deckofcardsapi.com/static/img/X2.png",
        null,
        "JOKER",
        "RED"),
)