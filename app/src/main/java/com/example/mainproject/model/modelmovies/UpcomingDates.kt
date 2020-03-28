package com.example.mainproject.model.modelmovies

import com.google.gson.annotations.SerializedName

data class UpcomingDates(

	@field:SerializedName("maximum")
	val maximum: String? = null,

	@field:SerializedName("minimum")
	val minimum: String? = null
)