package com.example.mainproject.Interface

import com.example.mainproject.model.model_discover.DiscoverMovieResponse
import com.example.mainproject.model.model_discover.DiscoverTvResponse
import com.example.mainproject.model.model_person.PersonResponse
import com.example.mainproject.model.model_tv.OnAirTvResponse
import com.example.mainproject.model.model_tv.PopularTvResponse
import com.example.mainproject.model.model_tv.TopRatedTvResponse
import com.example.mainproject.model.model_tv.cast.TvCastResponse
import com.example.mainproject.model.model_tv.detailss.Responsedd
import com.example.mainproject.model.model_tv.similartvshows.SimilarTvShowsResponse
import com.example.mainproject.model.modelmovies.*
import com.example.mainproject.model.modelmovies.credits.CreditResponse
import com.example.mainproject.model.modelmovies.details.Responsess
import com.example.mainproject.model.modelmovies.similarmovies.SimilarMoviesResponse
import com.example.mainproject.model.multisearch.SearchResponse
import com.example.mainproject.model.trailers.movietrailers.TrailerResponse
import com.example.mainproject.model.trailers.tvtrailers.TvTrailerResponse
import com.example.mainproject.model.trending.AllTrendingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Movie {

    //MOVIE
    @GET("movie/top_rated?")
    suspend fun getAllTopRatedMovie(@Query("page")page:String,
                                    @Query("api_key")api_key:String) :Response<TopRatedResponse>

    @GET("movie/now_playing?")
    suspend fun getAllRecentMovies(@Query("page")page:String,
                                   @Query("year")year:String,
                                   @Query("api_key")api_key:String):Response<NowPlayingResponse>

    @GET("movie/popular?")
    suspend fun getAllPopularMovie(@Query("page")page:String,
                                    @Query("api_key")api_key:String ) :Response<PopularResponse>

    @GET("movie/upcoming?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getAllUpcomingMovies(@Query("date")date:String,
                                     @Query("page")page:String):Response<UpcomingResponse>


    //TV-SHOWS
    @GET("tv/top_rated?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getAllTopRatedTvShows(@Query("page")page:String):Response<TopRatedTvResponse>

    @GET("tv/on_the_air?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getAllOnAirTvShows(@Query("page")page:String):Response<OnAirTvResponse>

    @GET("tv/popular?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getAllPopularTvShows(@Query("page")page:String):Response<PopularTvResponse>


    //DISCOVER
    @GET("discover/movie?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getAllDiscoveredMovies(@Query("page")page:String):Response<DiscoverMovieResponse>

    @GET("discover/tv?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getAllDiscoveredTvShows(@Query("page")page:String):Response<DiscoverTvResponse>


    //PERSON
    @GET("person/popular?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getAllPopularPerson(@Query("page")page:String):Response<PersonResponse>

    //MOVIE-DETAILS
    @GET("movie/{movie_id}?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getMovieTop(@Path("movie_id")movie_id:String):Response<Responsess>


    //MOVIE-CAST
    @GET("movie/{movie_id}/credits?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getMoviesCast(@Path("movie_id")movie_id:String):Response<CreditResponse>


    //TV-DETAILS
    @GET("tv/{tv_id}?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getTvTop(@Path("tv_id")tv_id:String):Response<Responsedd>

    //TV-CAST
    @GET("tv/{tv_id}/credits?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getTvshowCast(@Path("tv_id")tv_id:String):Response<TvCastResponse>


    //TRAILERS
    @GET("movie/{movie_id}/videos?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getMovieTrailers(@Path("movie_id")movie_id:String):Response<TrailerResponse>

    @GET("tv/{tv_id}/videos?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getTvTrailers(@Path("tv_id")tv_id:String):Response<TvTrailerResponse>

    //SIMILAR
    @GET("movie/{movie_id}/similar?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getSimilarMovies(@Path("movie_id")movie_id:String):Response<SimilarMoviesResponse>

    @GET("tv/{tv_id}/similar?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getSimilarTvShows(@Path("tv_id")tv_id:String):Response<SimilarTvShowsResponse>


    //TRENDING
    @GET("trending/{media_type}/day?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getTrendings(@Path("media_type")media_type:String):Response<AllTrendingResponse>


    //SEARCH
    @GET("search/multi?api_key=3b64ac412725369ae963817a7514e443")
    suspend fun getMultiSeach(@Query("query")query:String,
                              @Query("page")page:String):Response<SearchResponse>

}
