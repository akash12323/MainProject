package com.example.mainproject.model.model_person

import com.google.gson.annotations.SerializedName

data class PersonResponse(

    @field:SerializedName("page")
	val page: Int? = null,

    @field:SerializedName("total_pages")
	val totalPages: Int? = null,

    @field:SerializedName("results")
	val results: List<PersonPopular>? = null,

    @field:SerializedName("total_results")
	val totalResults: Int? = null
)