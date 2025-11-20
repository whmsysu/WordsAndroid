package com.example.wordsandroid

import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsandroid.data.GreVocabulary
import com.example.wordsandroid.databinding.ActivityMainBinding
import com.example.wordsandroid.ui.WordAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val wordViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as WordsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize data if needed
        wordViewModel.initializeData(GreVocabulary.loadWords(this))

        val adapter = WordAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean = false
        }
        
        // Use PagerSnapHelper to center the card
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerView)

        wordViewModel.dueWords.observe(this) { words ->
            if (words.isEmpty()) {
                binding.recyclerView.visibility = View.GONE
                binding.tvEmptyState.visibility = View.VISIBLE
            } else {
                binding.recyclerView.visibility = View.VISIBLE
                binding.tvEmptyState.visibility = View.GONE
                adapter.submitList(words)
            }
        }

        setupSwipeGestures(adapter)
    }

    private fun setupSwipeGestures(adapter: WordAdapter) {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            0, 
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val word = adapter.currentList[position]

                if (direction == ItemTouchHelper.LEFT) {
                    // Swipe Left -> Remember
                    wordViewModel.rememberWord(word)
                } else {
                    // Swipe Right -> Forgot
                    // Note: Usually Right is "Like/Yes" and Left is "Nope/No".
                    // User asked: "Left swipe = Remember, Right swipe = Forgot"
                    wordViewModel.forgetWord(word)
                }
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val width = recyclerView.width.toFloat()
                val alpha = 1.0f - Math.abs(dX) / width
                
                // Rotate based on dX
                // Max rotation 15 degrees
                val rotation = dX / width * 15f
                
                viewHolder.itemView.rotation = rotation
                viewHolder.itemView.alpha = alpha
                
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }

            override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
                super.clearView(recyclerView, viewHolder)
                viewHolder.itemView.rotation = 0f
                viewHolder.itemView.alpha = 1f
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }


    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_vocabulary_book -> {
                val intent = android.content.Intent(this, VocabularyBookActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
