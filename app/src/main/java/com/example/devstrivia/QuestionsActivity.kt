package com.example.devstrivia

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.devstrivia.databinding.ActivityQuestionsBinding

class QuestionsActivity : AppCompatActivity() {

    var finalPosition = 4
    var currentPosition = 1
    var correctAns = (1 .. 4).random()
    var state = 0
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityQuestionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_questions)
        showQuestion()
        currentPosition+=1
    }

    private fun showQuestion(){

        val questionList = Question_data.getQuestions()
        val question: Question = questionList[currentPosition-1]
        val questionView = findViewById<TextView>(R.id.questionView)
        val answerButtons = findViewById<RadioGroup>(R.id.radioGroup)
        val answerButton1 = findViewById<RadioButton>(R.id.radioButton1)
        val answerButton2 = findViewById<RadioButton>(R.id.radioButton2)
        val answerButton3 = findViewById<RadioButton>(R.id.radioButton3)
        val answerButton4 = findViewById<RadioButton>(R.id.radioButton4)
        answerButtons.clearCheck()
        answerButton1.text = question.correct_answer
        answerButton2.text = question.incorrect_answers[0]
        answerButton3.text = question.incorrect_answers[1]
        answerButton4.text = question.incorrect_answers[2]
        correctAns = (1 .. 4).random()
        when(correctAns){
            1 -> {correctAns = R.id.radioButton1}
            2 -> {answerButton2.text = question.correct_answer
                answerButton1.text = question.incorrect_answers[0]
                correctAns = R.id.radioButton2}
            3 -> {answerButton3.text = question.correct_answer
                answerButton1.text = question.incorrect_answers[1]
                correctAns = R.id.radioButton3}
            else -> {answerButton4.text = question.correct_answer
                answerButton1.text = question.incorrect_answers[2]
                correctAns = R.id.radioButton4}
        }
        questionView.text = question.question
        state = 1
    }

    fun checkAns(view: View) {
        if (state != 1){
            return
        }
        //Log.i("asdf","${view.id}")
        //val radioOptionsGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val selectedId: Int = view.id
        val correct = findViewById<RadioButton>(correctAns)
        val selected = findViewById<RadioButton>(selectedId)
        correct.setBackgroundResource(R.drawable.pngitem_correct)
        if (selectedId != correctAns){
            selected.setBackgroundResource(R.drawable.pngitem_wrong)
        }
        val button = findViewById<Button>(R.id.nextButton)
        button.visibility = Button.VISIBLE
        if (currentPosition == finalPosition+1){ button.text = getString(R.string.go_back) }
        state = 2


    }

    fun nextQuestion(view: View) {

        val answerButton1 = findViewById<RadioButton>(R.id.radioButton1)
        val answerButton2 = findViewById<RadioButton>(R.id.radioButton2)
        val answerButton3 = findViewById<RadioButton>(R.id.radioButton3)
        val answerButton4 = findViewById<RadioButton>(R.id.radioButton4)
        answerButton1.setBackgroundResource(R.drawable.pngitem_584233)
        answerButton2.setBackgroundResource(R.drawable.pngitem_584233)
        answerButton3.setBackgroundResource(R.drawable.pngitem_584233)
        answerButton4.setBackgroundResource(R.drawable.pngitem_584233)
        val button = findViewById<Button>(view.id)
        if (currentPosition == finalPosition+1){
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()}
        showQuestion()
        currentPosition+=1
        button.visibility = Button.GONE
    }
}