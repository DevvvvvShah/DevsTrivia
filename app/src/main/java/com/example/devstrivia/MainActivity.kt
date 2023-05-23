package com.example.devstrivia

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

var score = 0
var prevScore = 0
var hasPlayed = 0
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume(){
        super.onResume()
        if (hasPlayed != 0){
            val prevScoreMes = findViewById<TextView>(R.id.prevScoreMessage)
            val prevScoreText = getString(R.string.previous_score_edit, prevScore)
            prevScoreMes.text = prevScoreText
            prevScoreMes.visibility = TextView.VISIBLE
        }

    }

    fun nextPage(view: View) {
        val name = findViewById<EditText>(R.id.name)
        if (name.text.toString().isEmpty()){
            Toast.makeText(this, "Please write a name", Toast.LENGTH_SHORT).show()
        }
        else{
            val intent = Intent(this, QuestionsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


}