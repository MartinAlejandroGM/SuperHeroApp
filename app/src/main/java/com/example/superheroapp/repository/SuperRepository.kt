package com.example.superheroapp.repository

import com.example.superheroapp.models.SearchResponse
import com.example.superheroapp.models.SuperHeroResponse

interface SuperRepository {
    suspend fun getSuperHero(superId: Int): SuperHeroResponse?

    suspend fun searchSuperHero(nameSearch: String): List<SuperHeroResponse>?
}