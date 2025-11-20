package com.example.wordsandroid

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordsandroid.databinding.ActivityVocabularyBookBinding
import com.example.wordsandroid.ui.VocabularyAdapter

class VocabularyBookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVocabularyBookBinding
    private val wordViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as WordsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVocabularyBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Vocabulary Book"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = VocabularyAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        wordViewModel.vocabularyWords.observe(this) { words ->
            adapter.submitList(words)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
