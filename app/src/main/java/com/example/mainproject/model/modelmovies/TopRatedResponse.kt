package com.example.mainproject.model.modelmovies

import com.example.mainproject.model.modelmovies.MoviesTopRated
import com.google.gson.annotations.SerializedName

data class TopRatedResponse(

    @field:SerializedName("page")
	val page: Int? = 2,

    @field:SerializedName("total_pages")
	val totalPages: Int? = null,

    @field:SerializedName("results")
	val results: List<MoviesTopRated>? = null,

    @field:SerializedName("total_results")
	val totalResults: Int? = null
)