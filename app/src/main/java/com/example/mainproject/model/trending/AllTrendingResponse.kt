package com.example.mainproject.model.trending

import com.google.gson.annotations.SerializedName

data class AllTrendingResponse(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<TrendingResultsItem>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)