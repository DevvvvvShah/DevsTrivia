package com.example.devstrivia

data class Question(
    val id: Int,
    val question: String,
    val correct_answer: String,
    val incorrect_answers: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Question

        if (id != other.id) return false
        if (question != other.question) return false
        if (correct_answer != other.correct_answer) return false
        if (!incorrect_answers.contentEquals(other.incorrect_answers)) return false

        return true
    }


    override fun hashCode(): Int {
        var result = id
        result = 31 * result + question.hashCode()
        result = 31 * result + correct_answer.hashCode()
        result = 31 * result + incorrect_answers.contentHashCode()
        return result
    }
}

