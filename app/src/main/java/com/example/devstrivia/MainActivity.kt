package com.example.devstrivia

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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