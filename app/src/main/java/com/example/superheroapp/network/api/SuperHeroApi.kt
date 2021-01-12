package com.example.superheroapp.network.api

import com.example.superheroapp.models.SearchResponse
import com.example.superheroapp.models.SuperHeroResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroApi {
    @GET("{heroId}")
    suspend fun getSuperHeroById(@Path("heroId") heroId: Int): SuperHeroResponse?

    @GET("search/{name}")
    suspend fun searchSuperHero(@Path("name") name: String): SearchResponse
}