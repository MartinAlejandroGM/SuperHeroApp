package com.example.superheroapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.superheroapp.models.SearchResponse
import com.example.superheroapp.models.SuperHeroResponse
import com.example.superheroapp.repository.impl.SuperRepositoryImpl
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private val searchRepository = SuperRepositoryImpl()
    private val _searchRepository: MutableLiveData<List<SuperHeroResponse>?> = MutableLiveData()

    val searchLiveData: LiveData<List<SuperHeroResponse>?>
        get() = _searchRepository

    /*
    this error handler helps to handle the errors caused by no internet connections or errors caused in the scope
    Reference: https://kotlinlang.org/docs/reference/coroutines/exception-handling.html
     */
    private val errorHandler = CoroutineExceptionHandler { _, e ->
        Log.e("SearchViewModel", "error:${e.message}", e)
    }

    fun fetchSearch(search: String){
        if (search.length > 2) {
            viewModelScope.launch(Dispatchers.IO + errorHandler) {
                _searchRepository.postValue(searchRepository.searchSuperHero(search))
            }
        }
    }
}