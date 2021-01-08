package com.example.superheroapp.models

data class SuperHeroResponse(
    val id: Int,
    val name: String,
    val image: HeroImage
)

data class HeroImage(
    val url: String
)