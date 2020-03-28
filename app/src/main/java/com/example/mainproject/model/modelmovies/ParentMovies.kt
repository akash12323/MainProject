package com.example.mainproject.model.modelmovies

import com.example.mainproject.model.modelmovies.MoviesNowPlaying
import com.example.mainproject.model.modelmovies.MoviesPopular
import com.example.mainproject.model.modelmovies.MoviesTopRated
import com.example.mainproject.model.modelmovies.MoviesUpcoming

data class ParentMovies(
    val category : String,
    val topRated : List<MoviesTopRated>,
    val upcoming : List<MoviesUpcoming>,
    val popular : List<MoviesPopular>,
    val nowPlaying : List<MoviesNowPlaying>
)