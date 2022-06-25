package com.example.tasksocial.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val repository: MainRepository
) : ViewModel() {
    private val _allList: MutableLiveData<List<Any>> = MutableLiveData()
    val allList: LiveData<List<Any>> = _allList

    fun loadMainModels() {
        repository.getModels().let {
            _allList.postValue(it)
        }
    }
}

interface MainRepository  {
    fun getModels(): List<Any>
}