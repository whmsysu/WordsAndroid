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

class WordAdapter : ListAdapter<Word, WordAdapter.WordViewHolder>(WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val layoutFront: View = itemView.findViewById(R.id.layoutFront)
        private val layoutBack: View = itemView.findViewById(R.id.layoutBack)
        private val tvEnglish: TextView = itemView.findViewById(R.id.tvEnglish)
        private val tvChinese: TextView = itemView.findViewById(R.id.tvChinese)
        private val tvEnglishSmall: TextView = itemView.findViewById(R.id.tvEnglishSmall)
        
        private var isRevealed = false
        private var isAnimating = false

        init {
            // Set camera distance for 3D effect on the CardView itself
            val scale = itemView.resources.displayMetrics.density
            val cameraDistance = 8000 * scale
            itemView.cameraDistance = cameraDistance

            itemView.setOnClickListener {
                if (!isAnimating) {
                    flipCard()
                }
            }
        }

        private fun flipCard() {
            isAnimating = true
            
            // Rotate 90 degrees (first half)
            itemView.animate()
                .rotationY(90f)
                .setDuration(250)
                .withEndAction {
                    // Swap content visibility
                    if (isRevealed) {
                        // Show Front
                        layoutFront.visibility = View.VISIBLE
                        layoutBack.visibility = View.GONE
                        isRevealed = false
                    } else {
                        // Show Back
                        layoutBack.visibility = View.VISIBLE
                        layoutFront.visibility = View.GONE
                        isRevealed = true
                    }

                    // Prepare for second half: rotate to -90 immediately
                    itemView.rotationY = -90f
                    
                    // Rotate back to 0 (second half)
                    itemView.animate()
                        .rotationY(0f)
                        .setDuration(250)
                        .withEndAction {
                            isAnimating = false
                        }
                        .start()
                }
                .start()
        }

        fun bind(word: Word) {
            tvEnglish.text = word.english
            tvChinese.text = word.chinese
            tvEnglishSmall.text = word.english
            
            // Reset state
            isRevealed = false
            isAnimating = false
            
            // Ensure Front is visible, Back is gone
            layoutFront.visibility = View.VISIBLE
            layoutBack.visibility = View.GONE
            
            // Reset rotation and alpha
            itemView.rotationY = 0f
            itemView.alpha = 1f
        }

        companion object {
            fun create(parent: ViewGroup): WordViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_word_card, parent, false)
                return WordViewHolder(view)
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
