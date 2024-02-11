package com.example.decklist.data

data class Person(
    val name: String,
    val age: Int,
    val picture: String,
    val email: Contact,
    val linkedin: Contact,
    val github: Contact,
    val job: String,
    val description: String
)
