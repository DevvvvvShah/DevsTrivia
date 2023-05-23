package com.example.devstrivia

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity :AppCompatActivity(){
    override fun onPause() {
        super.onPause()
        val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putInt("score",score)
        editor.putInt("prevScore", prevScore)
        editor.putInt("hasPlayed", hasPlayed)
        editor.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putInt("score",score)
        editor.putInt("prevScore", prevScore)
        editor.putInt("hasPlayed", hasPlayed)
        editor.apply()
    }


    override fun onResume() {
        super.onResume()
        val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        score = sharedPreference.getInt("score",0)
        prevScore = sharedPreference.getInt("prevScore", 0)
        hasPlayed = sharedPreference.getInt("hasPlayed", 0)
    }
}