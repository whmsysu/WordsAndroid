package com.example.wordsandroid.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

object GreVocabulary {

    fun loadWords(context: Context): List<Word> {
        val assetManager = context.assets
        val inputStream = assetManager.open("GREWords.json")
        val reader = InputStreamReader(inputStream)
        val type = object : TypeToken<List<Word>>() {}.type
        val words: List<Word> = Gson().fromJson(reader, type)
        
        // Ensure initial stage is 0 if not specified (though JSON has it as 0 usually)
        // Also ensure nextReviewTime is 0 (due immediately)
        return words.map { it.copy(stage = 0, nextReviewTime = 0) }
    }
}
