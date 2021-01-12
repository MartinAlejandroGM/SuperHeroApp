package com.example.superheroapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.superheroapp.models.SuperHeroResponse
import com.example.superheroapp.repository.impl.SuperRepositoryImpl
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SuperViewModel(application: Application) : AndroidViewModel(application) {
    private val superHeroRepository = SuperRepositoryImpl()
    private val _superHeroLiveData: MutableLiveData<List<SuperHeroResponse>?> = MutableLiveData()

    var isLoading = false
    var currentHero = 1
    val superHeroLiveData: LiveData<List<SuperHeroResponse>?>
        get() = _superHeroLiveData

    /*
    this error handler helps to handle the errors caused by no internet connections or errors caused in the scope
    Reference: https://kotlinlang.org/docs/reference/coroutines/exception-handling.html
     */
    private val errorHandler = CoroutineExceptionHandler { _, e ->
        Log.e("SuperViewModel", "error:${e.message}", e)
    }

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
        viewModelScope.launch(Dispatchers.IO + errorHandler) {
            while (actualHero < lastHero) {
                superHeroRepository.getSuperHero(actualHero)?.run {
                    heroes.add(this)
                }
                actualHero++
            }
            isLoading = false
            _superHeroLiveData.postValue(heroes)
        }
    }
}