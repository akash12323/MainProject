package com.example.mainproject.model.trailers.tvtrailers

import com.google.gson.annotations.SerializedName

data class TvTrailerResponse(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("results")
	val results: List<TrailerTv>? = null
)