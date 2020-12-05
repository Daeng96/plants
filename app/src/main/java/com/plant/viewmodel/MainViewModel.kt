package com.plant.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.plant.pojo.Species

class MainViewModel : ViewModel() {

    private val _listPlants = MutableLiveData<Species>()
    val listPlants : LiveData<Species> = _listPlants

    fun setSpecies(Species: Species) {
        return _listPlants.postValue(Species)
    }
}