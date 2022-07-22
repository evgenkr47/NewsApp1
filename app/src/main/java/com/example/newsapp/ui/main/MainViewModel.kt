package com.example.newsapp.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.api.TestRepo
import com.example.newsapp.models.Article
import com.example.newsapp.models.NewsResponce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: TestRepo): ViewModel(){
    private val _all = MutableLiveData<NewsResponce>()
    val all: LiveData<NewsResponce>
        get() = _all

    init {
        getAll()
    }



    fun getAll() = viewModelScope.launch {
        repository.getAll().let {
            if (it.isSuccessful){
                _all.postValue(it.body())
            }
            else {
                Log.d("checkData", "Failed to load articles: ${it.errorBody()}")
            }
        }
    }


}