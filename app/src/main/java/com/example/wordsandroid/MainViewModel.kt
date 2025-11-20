package com.example.wordsandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.wordsandroid.data.Word
import com.example.wordsandroid.data.WordRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: WordRepository) : ViewModel() {
    val dueWords: LiveData<List<Word>> = repository.getDueWords().asLiveData()
    val allWords: LiveData<List<Word>> = repository.getAllWords().asLiveData()
    val vocabularyWords: LiveData<List<Word>> = repository.getVocabularyWords().asLiveData()

    fun rememberWord(word: Word) {
        viewModelScope.launch {
            repository.markAsRemembered(word)
        }
    }

    fun forgetWord(word: Word) {
        viewModelScope.launch {
            repository.markAsForgotten(word)
        }
    }
    
    fun initializeData(words: List<Word>) {
        viewModelScope.launch {
            repository.initializeData(words)
        }
    }
}

class MainViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
