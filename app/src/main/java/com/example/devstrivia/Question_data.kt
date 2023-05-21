package com.example.devstrivia


object Question_data{
    fun getQuestions() : ArrayList<Question>{
        val questionList = ArrayList<Question>()
        val que1 = Question(1,
            "What is the biggest planet in the solar system?",
            "Jupiter",
            arrayOf("Mars",
                "Venus",
                "Saturn"))
        val que2 = Question(2,
            "What is the smallest planet in the solar system?",
            "Mercury",
           arrayOf("Mars", "Venus","Saturn"))
        val que3 = Question(3,
            "What is the closest planet in the solar system?",
            "Mercury",
            arrayOf("Mars", "Venus","Saturn"))
        val que4 = Question(4,
            "What is the farthest planet in the solar system?",
            "Neptune",
            arrayOf("Mars", "Venus","Saturn"))
        questionList.add(que1)
        questionList.add(que2)
        questionList.add(que3)
        questionList.add(que4)
        return questionList
    }
}