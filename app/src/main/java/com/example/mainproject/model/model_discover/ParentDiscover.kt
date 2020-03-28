package com.example.mainproject.model.model_discover

import com.example.mainproject.model.model_discover.DiscoveredMovies
import com.example.mainproject.model.model_discover.DiscoveredTvShows

data class ParentDiscover(
    val heading:String,
    val discoveredTv : List<DiscoveredTvShows>,
    val discoveredMovies: List<DiscoveredMovies>
)