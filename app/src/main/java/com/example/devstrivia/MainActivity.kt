package com.example.devstrivia

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import org.w3c.dom.Text

var score = 0
var prevScore = 0
var hasPlayed = 0
var selectedCategory = 9
var selectedDifficulty = "easy"
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val catSpinner = findViewById<Spinner>(R.id.categorySpinner)
        val difSpinner = findViewById<Spinner>(R.id.difficultySpinner)
        val category = arrayListOf<String>("General Knowledge", "Video Games", "Geography", "Technology")
        val difficulty = arrayListOf<String>("Easy", "Medium", "Hard")
        val arrayAdapter1 = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,category)
        catSpinner.adapter = arrayAdapter1
        catSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedCategory = when (position){
                    0 -> 9
                    1-> 15
                    2-> 22
                    else-> 30
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedCategory = 0
            }
        }
        val arrayAdapter2 = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,difficulty)
        difSpinner.adapter = arrayAdapter2
        difSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedDifficulty = when(position){
                    0->"easy"
                    1->"medium"
                    else->"hard"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedDifficulty = "none"
            }
        }

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
        if (selectedCategory == 0 || selectedDifficulty == "null"){
            Toast.makeText(this, "Please select the details", Toast.LENGTH_SHORT).show()
        }
        else{
            val intent = Intent(this, QuestionsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


}