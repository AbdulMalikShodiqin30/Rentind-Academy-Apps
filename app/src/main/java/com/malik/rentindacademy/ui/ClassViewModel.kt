package com.malik.rentindacademy.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.malik.rentindacademy.data.ClassRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ClassViewModel(private val repository: ClassRepository) : ViewModel() {
    private val _classes = MutableStateFlow(
        repository.getClass()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )
    val classes: StateFlow<Map<Char, List<com.malik.rentindacademy.model.Class>>> get() = _classes

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        _classes.value = repository.searchedClass(_query.value)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }

    fun getDetailFood(id: Int) = repository.getDetailClass(id)
}

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: ClassRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClassViewModel::class.java)) {
            return ClassViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown view model class: " + modelClass.name)
    }
}