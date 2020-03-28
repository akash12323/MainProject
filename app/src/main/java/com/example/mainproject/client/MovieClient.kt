package com.example.mainproject.Client

import com.example.mainproject.Interface.Movie
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieClient {
    val gson = GsonBuilder()
        .create()
        //.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val api = retrofit.create(Movie::class.java)
}