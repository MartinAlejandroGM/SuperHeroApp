package com.example.superheroapp.models

data class SearchResponse(
    val response: String,
    val results: List<SuperHeroResponse>?
)