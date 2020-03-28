package com.example.mainproject.model.model_tv.cast

import com.google.gson.annotations.SerializedName

data class TvCastResponse(

	@field:SerializedName("cast")
	val cast: List<CastItem>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("crew")
	val crew: List<CrewItem>? = null
)