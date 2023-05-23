package com.example.devstrivia

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://opentdb.com/"
interface ApiInterface{
    @GET("api.php")

    fun getData(@Query("category") selectedCategory: Int, @Query("amount") amount: Int, @Query("difficulty") selectedDifficulty: String,@Query("type") type: String): Call<Res>
}

