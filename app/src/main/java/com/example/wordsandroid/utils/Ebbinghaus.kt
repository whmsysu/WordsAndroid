package com.example.wordsandroid.utils

object Ebbinghaus {
    // Stages in milliseconds: 5m, 30m, 12h, 1d, 2d, 4d, 7d, 15d
    private val STAGES = longArrayOf(
        5 * 60 * 1000L,       // 5 mins
        30 * 60 * 1000L,      // 30 mins
        12 * 60 * 60 * 1000L, // 12 hours
        24 * 60 * 60 * 1000L, // 1 day
        2 * 24 * 60 * 60 * 1000L, // 2 days
        4 * 24 * 60 * 60 * 1000L, // 4 days
        7 * 24 * 60 * 60 * 1000L, // 7 days
        15 * 24 * 60 * 60 * 1000L // 15 days
    )

    fun getNextReviewTime(currentStage: Int, currentTime: Long): Long {
        if (currentStage >= STAGES.size) {
            // If completed all stages, review in 15 days (maintenance)
            return currentTime + STAGES.last()
        }
        return currentTime + STAGES[currentStage]
    }
}
