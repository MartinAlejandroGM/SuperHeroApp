package com.example.superheroapp.repository.impl

import com.example.superheroapp.models.SuperHeroResponse
import com.example.superheroapp.network.webservice.SuperHeroWebService
import com.example.superheroapp.repository.SuperRepository

class SuperRepositoryImpl: SuperRepository {
    override suspend fun getSuperHero(superId: Int): SuperHeroResponse? {
        val superWebService = SuperHeroWebService()
        return superWebService.getSuperHeroById(superId)
    }

    override suspend fun searchSuperHero(nameSearch: String): List<SuperHeroResponse>? {
        val superWebService = SuperHeroWebService()
        val result = superWebService.searchHero(nameSearch)
        return result.results
    }
}