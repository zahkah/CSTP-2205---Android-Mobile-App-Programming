package com.databaselab_zga_15.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.databaselab_zga_15.repository.PersonRepository

class ViewModelFactory(private val repository: PersonRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PersonViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
