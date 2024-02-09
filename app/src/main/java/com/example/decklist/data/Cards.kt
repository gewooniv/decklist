package com.example.decklist.data

data class Cards(
    val code: String,
    val image: String,
    val images: Images?,
    val suit: String,
    val value: String
) {
    fun doesMatchSearchQuery(query: String): Boolean{
        val matchingCombinations = listOf(
            "$suit $value",
            "$value $suit",
            "$value of $suit"
        )

        return matchingCombinations.any{
            it.contains(query, ignoreCase = true)
        }
    }
}

data class CardsResponse(val cards: List<Cards>)