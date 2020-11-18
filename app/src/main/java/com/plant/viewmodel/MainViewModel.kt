package com.plant.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.plant.pojo.Tomato

class MainViewModel : ViewModel() {

    private val _listPlants = MutableLiveData<Tomato>()
    val listPlants : LiveData<Tomato> = _listPlants

    fun setTomato(tomato: Tomato) {
        return _listPlants.postValue(tomato)
    }
}