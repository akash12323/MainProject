package com.example.mainproject.model.model_tv

import com.google.gson.annotations.SerializedName

data class OnAirTvResponse(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<TvOnAir>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)