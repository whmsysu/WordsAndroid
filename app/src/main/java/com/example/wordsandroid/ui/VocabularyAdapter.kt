package com.example.wordsandroid.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsandroid.R
import com.example.wordsandroid.data.Word

class VocabularyAdapter : ListAdapter<Word, VocabularyAdapter.VocabularyViewHolder>(WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocabularyViewHolder {
        return VocabularyViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: VocabularyViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class VocabularyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvEnglish: TextView = itemView.findViewById(R.id.tvEnglish)
        private val tvChinese: TextView = itemView.findViewById(R.id.tvChinese)

        fun bind(word: Word) {
            tvEnglish.text = word.english
            tvChinese.text = word.chinese
        }

        companion object {
            fun create(parent: ViewGroup): VocabularyViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_vocabulary_word, parent, false)
                return VocabularyViewHolder(view)
            }
        }
    }

    class WordsComparator : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem == newItem
        }
    }
}
