package com.example.decklist.services

import com.example.decklist.data.CardsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val retrofit = Retrofit.Builder().baseUrl("https://www.deckofcardsapi.com/api/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

    val cardService: ApiService = retrofit.create(ApiService::class.java)

interface ApiService {
    @GET("deck/new/draw/?count=52")
    suspend fun getCards():CardsResponse
}