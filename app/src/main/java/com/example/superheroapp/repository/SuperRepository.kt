package com.example.superheroapp.repository

import com.example.superheroapp.models.SuperHeroResponse

interface SuperRepository {
    suspend fun getSuperHero(superId: Int): SuperHeroResponse
}