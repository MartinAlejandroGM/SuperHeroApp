package com.example.superheroapp.network.webservice

import com.example.superheroapp.models.SearchResponse
import com.example.superheroapp.models.SuperHeroResponse
import com.example.superheroapp.network.Api
import com.example.superheroapp.network.api.SuperHeroApi

class SuperHeroWebService {
    private val service = Api.createApi(SuperHeroApi::class.java)

    suspend fun getSuperHeroById(superId: Int): SuperHeroResponse?{
        return service.getSuperHeroById(superId)
    }

    suspend fun searchHero(search: String): SearchResponse{
        return service.searchSuperHero(search)
    }
}