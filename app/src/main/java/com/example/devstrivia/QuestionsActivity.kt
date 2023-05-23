package com.example.devstrivia

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class QuestionsActivity : BaseActivity() {

    private var finalPosition = 4
    var currentPosition = 1
    var correctAns = (1 .. 4).random()
    private var state = 0
    val questionList = ArrayList<Question>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_questions)
        val button = findViewById<Button>(R.id.nextButton)
        button.visibility = Button.GONE
        val scoreBut = findViewById<Button>(R.id.scoreId)
        //scoreBut.visibility = Button.GONE
        score = 0
        fetchQuestion()
    }

    private fun fetchQuestion(){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)
        val retrofitData = retrofitBuilder.getData()
        retrofitData.enqueue(object : Callback<Res?> {
            override fun onResponse(call: Call<Res?>, response: Response<Res?>) {
                val responseBody = response.body()!!
                val dataBody = responseBody.results
                Log.i("Question_data", "${responseBody.results}")
                for (data in dataBody) questionList.add(data)
                showQuestion()
            }
            override fun onFailure(call: Call<Res?>, t: Throwable) {
                Log.d("Question_data","failed")
            }
        })
    }
    private fun showQuestion(){

        finalPosition = questionList.count()
        val question: Question = questionList[currentPosition-1]
        val questionView = findViewById<TextView>(R.id.questionView)
        val answerButtons = findViewById<RadioGroup>(R.id.radioGroup)
        val answerButton1 = findViewById<RadioButton>(R.id.radioButton1)
        val answerButton2 = findViewById<RadioButton>(R.id.radioButton2)
        val answerButton3 = findViewById<RadioButton>(R.id.radioButton3)
        val answerButton4 = findViewById<RadioButton>(R.id.radioButton4)
        answerButtons.clearCheck()
        val incorrectAns = question.incorrect_answers.toTypedArray()
        answerButton1.text = question.correct_answer
        answerButton2.text = incorrectAns[0]
        answerButton3.text = incorrectAns[1]
        answerButton4.text = incorrectAns[2]
        correctAns = (1 .. 4).random()
        when(correctAns){
            1 -> {correctAns = R.id.radioButton1 }
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
        val questionText = getString(R.string.questionText,currentPosition,question.question)
        questionView.text = questionText
        currentPosition+=1
        state = 1
    }

    fun checkAns(view: View) {
        if (state != 1){
            return
        }
        //val radioOptionsGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val selectedId: Int = view.id
        val correct = findViewById<RadioButton>(correctAns)
        val selected = findViewById<RadioButton>(selectedId)
        correct.setBackgroundResource(R.drawable.pngitem_correct)
        if (selectedId != correctAns){
            selected.setBackgroundResource(R.drawable.pngitem_wrong)
        }
        else{ score+=1 }
        val scoreBut = findViewById<Button>(R.id.scoreId)
        scoreBut.visibility = Button.VISIBLE
        scoreBut.text = score.toString()
        val button = findViewById<Button>(R.id.nextButton)
        button.visibility = Button.VISIBLE
        if (currentPosition == finalPosition+1){ button.text = getString(R.string.goto_results) }
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
            hasPlayed = 1
            val intent = Intent(this@QuestionsActivity, ResultActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
            return}
        else{showQuestion()
        button.visibility = Button.GONE}
        Log.i("zxcv","${currentPosition -finalPosition-1}")
    }
}