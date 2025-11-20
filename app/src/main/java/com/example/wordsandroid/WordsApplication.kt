package com.example.wordsandroid

import android.app.Application
import com.example.wordsandroid.data.AppDatabase
import com.example.wordsandroid.data.WordRepository

class WordsApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { WordRepository(database.wordDao()) }
}
