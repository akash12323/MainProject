package com.example.mainproject.model.modelmovies

import com.example.mainproject.model.modelmovies.MoviesPopular
import com.google.gson.annotations.SerializedName

data class PopularResponse(

    @field:SerializedName("page")
	val page: Int? = null,

    @field:SerializedName("total_pages")
	val totalPages: Int? = null,

    @field:SerializedName("results")
	val results: List<MoviesPopular>? = null,

    @field:SerializedName("total_results")
	val totalResults: Int? = null
)