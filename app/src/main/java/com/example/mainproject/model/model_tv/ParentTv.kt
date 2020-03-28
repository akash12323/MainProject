package com.example.mainproject.model.model_tv

import com.example.mainproject.model.model_tv.TvOnAir
import com.example.mainproject.model.model_tv.TvPopular
import com.example.mainproject.model.model_tv.TvTopRated

data class ParentTv(
    val category : String,
    val topRatedTv : List<TvTopRated>,
    val onAir : List<TvOnAir>,
    val popularTv : List<TvPopular>
)