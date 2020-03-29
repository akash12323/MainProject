package com.example.mainproject.Interface

import com.example.mainproject.model.modelmovies.details.Responsess
import com.example.mainproject.model.model_discover.DiscoverMovieResponse
import com.example.mainproject.model.model_discover.DiscoverTvResponse
import com.example.mainproject.model.model_person.PersonResponse
import com.example.mainproject.model.model_tv.OnAirTvResponse
import com.example.mainproject.model.model_tv.PopularTvResponse
import com.example.mainproject.model.model_tv.TopRatedTvResponse
import com.example.mainproject.model.model_tv.cast.TvCastResponse
import com.example.mainproject.model.model_tv.detailss.Responsedd
import com.example.mainproject.model.model_tv.similartvshows.SimilarTvShowsResponse
import com.example.mainproject.model.modelmovies.NowPlayingResponse
import com.example.mainproject.model.modelmovies.PopularResponse
import com.example.mainproject.model.modelmovies.TopRatedResponse
import com.example.mainproject.model.modelmovies.UpcomingResponse
import com.example.mainproject.model.modelmovies.credits.CreditResponse
import com.example.mainproject.model.modelmovies.similarmovies.SimilarMoviesResponse
import com.example.mainproject.model.multisearch.SearchResponse
import com.example.mainproject.model.trailers.movietrailers.TrailerResponse
import com.example.mainproject.model.trailers.tvtrailers.TvTrailerResponse
import com.example.mainproject.model.trending.AllTrendingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//?api_key=3b64ac412725369ae963817a7514e443&page=1
//?api_key=3b64ac412725369ae963817a7514e443&page=1
//?api_key=3b64ac412725369ae963817a7514e443&primary_release_year=2020

interface Movie {

    @GET("movie/popular")
    suspend fun getAllPopularMovie(@Query("page")page:String,
                                   @Query("api_key")api_key:String): Response<PopularResponse>

    @GET("movie/top_rated")
    suspend fun getAllTopRatedMovie(@Query("page")page:String,
                                    @Query("api_key")api_key:String):Response<TopRatedResponse>

    @GET("movie/upcoming?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getAllUpcomingMovies(@Query("primary_release_date.gte")primary_release_date:String,
                                     @Query("page")page:String):Response<UpcomingResponse>

    @GET("movie/now_playing")
    suspend fun getAllRecentMovies(@Query("page")page:String,
                                   @Query("primary_release_year")primary_release_year:String,
                                   @Query("api_key")api_key:String):Response<NowPlayingResponse>

    @GET("tv/popular?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getAllPopularTvShows(@Query("page")page:String):Response<PopularTvResponse>

    @GET("tv/top_rated?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getAllTopRatedTvShows(@Query("page")page:String):Response<TopRatedTvResponse>

    @GET("tv/on_the_air?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getAllOnAirTvShows(@Query("page")page:String):Response<OnAirTvResponse>

    @GET("person/popular?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getAllPopularPerson(@Query("page")page:String):Response<PersonResponse>

    @GET("discover/movie?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getAllDiscoveredMovies(@Query("page")page:String):Response<DiscoverMovieResponse>

    @GET("discover/tv?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getAllDiscoveredTvShows(@Query("page")page:String):Response<DiscoverTvResponse>

    @GET("movie/{movie_id}/videos?api_key=3b64ac412725369ae963817a7514e443&language=en-US")
    suspend fun getMovieTrailers(@Path("movie_id")movie_id:String):Response<TrailerResponse>

    @GET("movie/{movie_id}?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getMovieTop(@Path("movie_id")movie_id:String):Response<Responsess>

    @GET("tv/{tv_id}?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getTvTop(@Path("tv_id")movie_id:String):Response<Responsedd>

    @GET("movie/{movie_id}/similar?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getSimilarMovies(@Path("movie_id")movie_id:String):Response<SimilarMoviesResponse>

    @GET("tv/{tv_id}/videos?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getTvTrailers(@Path("tv_id")tv_id:String):Response<TvTrailerResponse>

    @GET("/tv/{tv_id}/similar?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getSimilarTvShows(@Path("tv_id")tv_id:String):Response<SimilarTvShowsResponse>

    @GET("movie/{movie_id}/credits?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getMoviesCast(@Path("movie_id")movie_id:String):Response<CreditResponse>

    @GET("tv/{tv_id}/credits?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getTvshowCast(@Path("tv_id")tv_id:String):Response<TvCastResponse>

    @GET("search/multi?api_key=3b64ac412725369ae963817a7514e443&language=en-US")
    suspend fun getMultiSeach(@Query("query")query:String,
                              @Query("page")page:String):Response<SearchResponse>

    @GET("trending/all/day?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getTrendings():Response<AllTrendingResponse>
}

//https://api.themoviedb.org/3/movie/19404/videos?api_key=3b64ac412725369ae963817a7514e443&language=en-US
//https://api.themoviedb.org/3/movie/{movie_id}/videos?api_key=<<api_key>>&language=en-US
//
//Since we link to YouTube, it's as simple as taking the key value and appending it to a youtube.com address like so:
//
//http://api.themoviedb.org/3/movie/550/videos?api_key=###
//Has the YouTube key of SUXWAEX2jlg, so:
//
//https://www.youtube.com/watch?v=SUXWAEX2jlg
