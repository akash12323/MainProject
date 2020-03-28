package com.example.mainproject.model.modelmovies

import com.example.mainproject.model.modelmovies.MoviesUpcoming
import com.example.mainproject.model.modelmovies.UpcomingDates
import com.google.gson.annotations.SerializedName

data class UpcomingResponse(

    @field:SerializedName("dates")
	val dates: UpcomingDates? = null,

    @field:SerializedName("page")
	val page: Int? = null,

    @field:SerializedName("total_pages")
	val totalPages: Int? = null,

    @field:SerializedName("results")
	val results: List<MoviesUpcoming>? = null,

    @field:SerializedName("total_results")
	val totalResults: Int? = null
)