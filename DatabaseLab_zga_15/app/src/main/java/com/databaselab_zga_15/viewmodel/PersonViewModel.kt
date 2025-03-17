package com.databaselab_zga_15.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.databaselab_zga_15.model.Person
import com.databaselab_zga_15.repository.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PersonViewModel(private val repository: PersonRepository) : ViewModel() {
    val allPersons: Flow<List<Person>> = repository.allPersons

    fun insert(person: Person) = viewModelScope.launch { repository.insert(person) }
    fun update(person: Person) = viewModelScope.launch { repository.update(person) }
    fun delete(person: Person) = viewModelScope.launch { repository.delete(person) }
}
