package com.example.devstrivia

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class ResultActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val scoreView = findViewById<TextView>(R.id.scoreMessage)
        val scoreText = getString(R.string.your_score_edit, score)
        scoreView.text = scoreText
    }

    override fun onPause() {
        super.onPause()
        val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putInt("score",score)
        editor.putInt("prevScore", prevScore)
        editor.apply()
    }
    fun backStart(view: View) {
        prevScore = score
        score = 0
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }
}