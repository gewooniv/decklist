package com.example.decklist.data

data class Decks(
    val cards: List<Cards>,
    val deck_id: String,
    val remaining: Int,
    val success: Boolean
)

data class DecksResponse(val deck_id: String)