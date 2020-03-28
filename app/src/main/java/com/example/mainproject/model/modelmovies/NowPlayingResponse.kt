package com.example.mainproject.model.modelmovies

import com.google.gson.annotations.SerializedName

data class NowPlayingResponse(

    @field:SerializedName("dates")
	val dates: UpcomingDates? = null,

    @field:SerializedName("page")
	val page: Int? = null,

    @field:SerializedName("total_pages")
	val totalPages: Int? = null,

    @field:SerializedName("results")
	val results: List<MoviesNowPlaying>? = null,

    @field:SerializedName("total_results")
	val totalResults: Int? = null
)