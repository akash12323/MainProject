package com.example.mainproject.model.trailers.movietrailers

import com.example.mainproject.model.trailers.movietrailers.ResultsItem
import com.google.gson.annotations.SerializedName

data class TrailerResponse(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem>? = null
)