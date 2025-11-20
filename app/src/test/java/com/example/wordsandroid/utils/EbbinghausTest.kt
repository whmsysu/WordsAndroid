package com.example.wordsandroid.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class EbbinghausTest {

    @Test
    fun testNextReviewTime() {
        val currentTime = 1000000L
        
        // Stage 0 -> 5 mins (300,000 ms)
        assertEquals(currentTime + 300000L, Ebbinghaus.getNextReviewTime(0, currentTime))
        
        // Stage 1 -> 30 mins (1,800,000 ms)
        assertEquals(currentTime + 1800000L, Ebbinghaus.getNextReviewTime(1, currentTime))
        
        // Stage 7 -> 15 days
        val day = 24 * 60 * 60 * 1000L
        assertEquals(currentTime + 15 * day, Ebbinghaus.getNextReviewTime(7, currentTime))
        
        // Stage 8 (Maintenance) -> 15 days
        assertEquals(currentTime + 15 * day, Ebbinghaus.getNextReviewTime(8, currentTime))
    }
}
