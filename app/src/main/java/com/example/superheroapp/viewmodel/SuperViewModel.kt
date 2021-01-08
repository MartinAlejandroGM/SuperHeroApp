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

    val superHeroLiveData: LiveData<List<SuperHeroResponse>>
        get() = _superHeroRepository

    fun fetchFirstPage(){

    }

    fun fetchNextPage(nextItem: Int){

    }

    private fun fetchSuperHero(nextItem: Int){

    }
}