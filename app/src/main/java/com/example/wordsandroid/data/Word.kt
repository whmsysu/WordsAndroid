package com.example.wordsandroid.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class Word(
    @PrimaryKey val id: String,
    val english: String,
    val chinese: String,
    val stage: Int = 0, // Ebbinghaus stage (0-7)
    val nextReviewTime: Long = 0, // Timestamp in milliseconds
    val isInVocabularyBook: Boolean = false
)
