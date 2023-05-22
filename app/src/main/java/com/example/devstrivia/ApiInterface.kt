package com.example.devstrivia

import retrofit2.Call
import retrofit2.http.GET

const val BASE_URL = "https://opentdb.com/"
interface ApiInterface{
    @GET("api.php?amount=10&type=multiple")

    fun getData(): Call<Res>
}

