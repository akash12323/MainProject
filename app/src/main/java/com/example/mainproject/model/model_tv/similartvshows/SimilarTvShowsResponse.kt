package com.example.mainproject.model.model_tv.similartvshows

import com.google.gson.annotations.SerializedName

data class SimilarTvShowsResponse(

    @field:SerializedName("page")
	val page: Int? = null,

    @field:SerializedName("total_pages")
	val totalPages: Int? = null,

    @field:SerializedName("results")
	val results: List<SimilarTv>? = null,

    @field:SerializedName("total_results")
	val totalResults: Int? = null
)