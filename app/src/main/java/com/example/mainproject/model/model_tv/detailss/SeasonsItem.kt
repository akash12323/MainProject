package com.example.mainproject.model.model_tv.detailss

import com.google.gson.annotations.SerializedName

data class SeasonsItem(

	@field:SerializedName("air_date")
	val airDate: Any? = null,

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("episode_count")
	val episodeCount: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("season_number")
	val seasonNumber: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("poster_path")
	val posterPath: Any? = null
)