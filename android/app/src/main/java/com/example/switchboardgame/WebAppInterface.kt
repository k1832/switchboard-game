package com.example.switchboardgame

import android.content.Context
import android.webkit.JavascriptInterface
import android.widget.Toast

class WebAppInterface(private val mContext: Context) {

    companion object {
        private const val PREFS_NAME = "SwitchboardGamePrefs"
        private const val HIGH_SCORE_KEY = "highScore"
    }

    @JavascriptInterface
    fun saveScore(score: Int) {
        val prefs = mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val currentHighScore = prefs.getInt(HIGH_SCORE_KEY, 0)

        if (score > currentHighScore) {
            with(prefs.edit()) {
                putInt(HIGH_SCORE_KEY, score)
                apply()
            }
        }
    }

    @JavascriptInterface
    fun getHighScore(): Int {
        val prefs = mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getInt(HIGH_SCORE_KEY, 0)
    }
}
