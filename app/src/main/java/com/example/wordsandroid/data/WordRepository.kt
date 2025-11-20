package com.example.wordsandroid.data

import com.example.wordsandroid.utils.Ebbinghaus
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    fun getDueWords(): Flow<List<Word>> {
        return wordDao.getDueWords(System.currentTimeMillis())
    }

    fun getAllWords(): Flow<List<Word>> {
        return wordDao.getAllWords()
    }

    fun getVocabularyWords(): Flow<List<Word>> {
        return wordDao.getVocabularyWords()
    }

    suspend fun markAsRemembered(word: Word) {
        val nextStage = word.stage + 1
        val nextReview = Ebbinghaus.getNextReviewTime(nextStage, System.currentTimeMillis())
        // Remove from vocabulary book when remembered
        val updatedWord = word.copy(stage = nextStage, nextReviewTime = nextReview, isInVocabularyBook = false)
        wordDao.update(updatedWord)
    }

    suspend fun markAsForgotten(word: Word) {
        // Reset to stage 0, review in 5 minutes
        val nextStage = 0
        val nextReview = Ebbinghaus.getNextReviewTime(nextStage, System.currentTimeMillis())
        // Add to vocabulary book when forgotten (swiped right)
        val updatedWord = word.copy(stage = nextStage, nextReviewTime = nextReview, isInVocabularyBook = true)
        wordDao.update(updatedWord)
    }
    
    suspend fun initializeData(words: List<Word>) {
        if (wordDao.getCount() == 0) {
            wordDao.insertAll(words)
        }
    }
}
