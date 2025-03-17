package com.lab6_zga_15.entities_zga_15

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WordListViewModel : ViewModel() {
    private val _wordList = MutableStateFlow(listOf("apples", "oranges", "bananas"))
    val wordList = _wordList.asStateFlow()

    private val _editingIndex = MutableStateFlow<Int?>(null)
    val editingIndex = _editingIndex.asStateFlow()

    private val _currentWord = MutableStateFlow("")
    val currentWord = _currentWord.asStateFlow()

    fun addOrUpdateWord() {
        _editingIndex.value?.let { index ->
            _wordList.value = _wordList.value.toMutableList().apply { set(index, _currentWord.value) }
            _editingIndex.value = null
        } ?: run {
            _wordList.value += _currentWord.value
        }
        _currentWord.value = "" // Clear input field after action
    }

    fun deleteWord(index: Int) {
        if (index in _wordList.value.indices) {
            _wordList.value = _wordList.value.toMutableList().apply { removeAt(index) }
        }
    }

    fun selectWordForEditing(index: Int) {
        _currentWord.value = _wordList.value[index]
        _editingIndex.value = index
    }

    fun updateCurrentWord(word: String) {
        _currentWord.value = word
    }
}
