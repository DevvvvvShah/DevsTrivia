package com.example.devstrivia

data class Res(
    val response_code: Int,
    val results: List<Question>
)
data class Question(
    val category: String,
    val difficulty: String,
    val type: String,
    val question: String,
    val correct_answer: String,
    val incorrect_answers: List<String>
) {
}

