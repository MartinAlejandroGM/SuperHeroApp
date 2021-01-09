package com.example.superheroapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.superheroapp.models.SuperHeroResponse
import com.example.superheroapp.repository.impl.SuperRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SuperViewModel(application: Application) : AndroidViewModel(application) {
    private val superHeroRepository = SuperRepositoryImpl()
    private val _superHeroRepository: MutableLiveData<List<SuperHeroResponse>> = MutableLiveData()

    var isLoading = false
    var currentHero = 1
    val superHeroLiveData: LiveData<List<SuperHeroResponse>>
        get() = _superHeroRepository

    fun fetchFirstPage() {
        currentHero = 1
        if (!isLoading) {
            fetchSuperHero(currentHero)
        }
    }

    fun fetchNextPage() {
        if (!isLoading) {
            currentHero += 10
            fetchSuperHero(currentHero)
        }
    }

    private fun fetchSuperHero(nextItem: Int) {
        isLoading = true
        val heroes: ArrayList<SuperHeroResponse> = ArrayList()
        var actualHero = nextItem
        val lastHero = nextItem + 10
        viewModelScope.launch(Dispatchers.IO) {
            while (actualHero < lastHero) {
                heroes.add(superHeroRepository.getSuperHero(actualHero))
                actualHero++
            }
            isLoading = false
            _superHeroRepository.postValue(heroes)
        }
    }
}