package com.example.mainproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproject.Client.MovieClient
import com.example.mainproject.adapter.discoveradapter.newdiscoveradapter.NewDiscoverMovieAdapter
import com.example.mainproject.adapter.discoveradapter.newdiscoveradapter.NewDiscoverTvAdapter
import com.example.mainproject.adapter.movieadapter.newmovieadapter.NewNowPlayingChildAdapter
import com.example.mainproject.adapter.movieadapter.newmovieadapter.NewPopularChildAdapter
import com.example.mainproject.adapter.movieadapter.newmovieadapter.NewTopRatedChildAdapter
import com.example.mainproject.adapter.movieadapter.newmovieadapter.NewUpcomingChildAdapter
import com.example.mainproject.adapter.tvadapter.newtvadapter.NewTvOnAirAdapter
import com.example.mainproject.adapter.tvadapter.newtvadapter.NewTvPopularAdapter
import com.example.mainproject.adapter.tvadapter.newtvadapter.NewTvTopRatedAdapter
import com.example.mainproject.model.modelmovies.MoviesNowPlaying
import com.example.mainproject.model.modelmovies.MoviesPopular
import com.example.mainproject.model.modelmovies.MoviesTopRated
import com.example.mainproject.model.modelmovies.MoviesUpcoming
import com.example.mainproject.model.model_discover.DiscoveredMovies
import com.example.mainproject.model.model_discover.DiscoveredTvShows
import com.example.mainproject.model.model_tv.TvOnAir
import com.example.mainproject.model.model_tv.TvPopular
import com.example.mainproject.model.model_tv.TvTopRated
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Main2Activity : AppCompatActivity() {

    val list1 = arrayListOf<MoviesTopRated>()
    val topratedAdapter = NewTopRatedChildAdapter(list1)

    val list2 = arrayListOf<MoviesPopular>()
    val popularAdapter = NewPopularChildAdapter(list2)

    val list3 = arrayListOf<MoviesUpcoming>()
    val upcomingAdapter = NewUpcomingChildAdapter(list3)

    val list4 = arrayListOf<MoviesNowPlaying>()
    val nowPlayingAdapter = NewNowPlayingChildAdapter(list4)

    val list5 = arrayListOf<TvTopRated>()
    val tvTopRatedAdapter = NewTvTopRatedAdapter(list5)

    val list6 = arrayListOf<TvOnAir>()
    val tvOnAirAdapter = NewTvOnAirAdapter(list6)

    val list7 = arrayListOf<TvPopular>()
    val tvPopularAdapter = NewTvPopularAdapter(list7)

    val list8 = arrayListOf<DiscoveredTvShows>()
    val discoverTvAdapter = NewDiscoverTvAdapter(list8)

    val list9 = arrayListOf<DiscoveredMovies>()
    val discoverMovieAdapter = NewDiscoverMovieAdapter(list9)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var s = intent.getStringExtra("text")

        var c = intent.getStringExtra("category")

        var h = intent.getStringExtra("heading")

        if(s == "TOP-RATED MOVIES"){
            tv2.text = s
            rView2.apply {
                layoutManager = GridLayoutManager(context,1, RecyclerView.VERTICAL,false)
                adapter = topratedAdapter
            }
            topratedAdapter.onItemClick = {
                var i = Intent(this,Main6Activity::class.java)

                i.putExtra("id",it.id.toString())

                startActivity(i)
                Toast.makeText(this,"${it.title}",Toast.LENGTH_SHORT).show()
            }
            tv2.visibility = View.INVISIBLE
            GlobalScope.launch {
                for (i in 1..10){
                    val response1 = withContext(Dispatchers.IO){ MovieClient.api.getAllTopRatedMovie("${i}",
                        "3b64ac412725369ae963817a7514e443") }
                    if(response1.isSuccessful){
                        response1.body()?.let {res ->
                            res.results?.let { list1.addAll(it) }
                            runOnUiThread{
                                topratedAdapter.notifyDataSetChanged()
                                pBar.visibility = View.INVISIBLE
                                tv2.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            }
        }else if (s == "POPULAR MOVIES"){
            tv2.text = s
            rView2.apply {
                layoutManager = GridLayoutManager(context,1, RecyclerView.VERTICAL,false)
                adapter = popularAdapter
            }
            popularAdapter.onItemClick = {
                var i = Intent(this,Main6Activity::class.java)

//                i.putExtra("ID",it.id)
                i.putExtra("id",it.id.toString())

                startActivity(i)
                Toast.makeText(this,"${it.title}",Toast.LENGTH_SHORT).show()
            }
            tv2.visibility = View.INVISIBLE
            GlobalScope.launch {
                for(i in 1..10){
                    val response1 = withContext(Dispatchers.IO){
                        MovieClient.api.getAllPopularMovie("${i}","3b64ac412725369ae963817a7514e443") }
                    if(response1.isSuccessful){
                        response1.body()?.let {res ->
                            res.results?.let { list2.addAll(it) }
                            runOnUiThread{
                                popularAdapter.notifyDataSetChanged()
                                pBar.visibility = View.INVISIBLE
                                tv2.visibility = View.VISIBLE
                            }
                        }
                        //tv2.visibility = View.VISIBLE
                    }
                }
            }
        }else if (s == "UPCOMING MOVIES"){
            tv2.text = s
            rView2.apply {
                layoutManager = GridLayoutManager(context,1, RecyclerView.VERTICAL,false)
                adapter = upcomingAdapter
            }
            upcomingAdapter.onItemClick = {
                var i = Intent(this,Main6Activity::class.java)

                i.putExtra("id",it.id.toString())

                startActivity(i)
                Toast.makeText(this,"${it.title}",Toast.LENGTH_SHORT).show()
            }
            tv2.visibility = View.INVISIBLE
            GlobalScope.launch {
                for (i in 1..10){
                    val response1 = withContext(Dispatchers.IO){
                        MovieClient.api.getAllUpcomingMovies("2020-03-17","${i}") }
                    if(response1.isSuccessful){
                        response1.body()?.let {res ->
                            res.results?.let { list3.addAll(it) }
                            runOnUiThread{
                                upcomingAdapter.notifyDataSetChanged()
                                pBar.visibility = View.INVISIBLE
                                tv2.visibility = View.VISIBLE
                            }
                        }
                        //tv2.visibility = View.VISIBLE
                    }
                }
            }
        }else if (s == "NOW-PLAYING MOVIES"){
            tv2.text = s
            rView2.apply {
                layoutManager = GridLayoutManager(context,1, RecyclerView.VERTICAL,false)
                adapter = nowPlayingAdapter
            }
            nowPlayingAdapter.onItemClick = {
                var i = Intent(this,Main6Activity::class.java)

                i.putExtra("id",it.id.toString())

                startActivity(i)
                Toast.makeText(this,"${it.title}",Toast.LENGTH_SHORT).show()
            }
            tv2.visibility = View.INVISIBLE
            GlobalScope.launch {
                for (i in 1..10){
                    val response1 = withContext(Dispatchers.IO){
                        MovieClient.api.getAllRecentMovies("${i}","2020","3b64ac412725369ae963817a7514e443") }
                    if(response1.isSuccessful){
                        response1.body()?.let {res ->
                            res.results?.let { list4.addAll(it) }
                            runOnUiThread{
                                nowPlayingAdapter.notifyDataSetChanged()
                                tv2.visibility = View.VISIBLE
                                pBar.visibility = View.INVISIBLE
                            }
                        }
                        //tv2.visibility = View.VISIBLE
                    }
                }
            }
        }

        if (c == "TOP-RATED TV SHOWS"){
            tv2.text = c
            rView2.apply {
                layoutManager = GridLayoutManager(context,1, RecyclerView.VERTICAL,false)
                adapter = tvTopRatedAdapter
            }
            tvTopRatedAdapter.onItemClick = {
                var i = Intent(this,Main7Activity::class.java)

                i.putExtra("id",it.id.toString())

                Toast.makeText(this,"${it.name}",Toast.LENGTH_SHORT).show()

                startActivity(i)
            }
            tv2.visibility = View.INVISIBLE
            GlobalScope.launch {
                for (i in 1..10){
                    val response1 = withContext(Dispatchers.IO){ MovieClient.api.getAllTopRatedTvShows("${i}") }
                    if(response1.isSuccessful){
                        response1.body()?.let {res ->
                            res.results?.let { list5.addAll(it) }
                            runOnUiThread{
                                tvTopRatedAdapter.notifyDataSetChanged()
                                tv2.visibility = View.VISIBLE
                                pBar.visibility = View.INVISIBLE
                            }
                        }
                        //tv2.visibility = View.VISIBLE
                    }
                }
            }
        }else if (c == "ON-AIR TV SHOWS"){
            tv2.text = c
            rView2.apply {
                layoutManager = GridLayoutManager(context,1, RecyclerView.VERTICAL,false)
                adapter = tvOnAirAdapter
            }
            tvOnAirAdapter.onItemClick = {
                var i = Intent(this,Main7Activity::class.java)

                i.putExtra("id",it.id.toString())

                Toast.makeText(this,"${it.name}",Toast.LENGTH_SHORT).show()

                startActivity(i)
            }
            tv2.visibility = View.INVISIBLE
            GlobalScope.launch {
                for (i in 1..10){
                    val response1 = withContext(Dispatchers.IO){ MovieClient.api.getAllOnAirTvShows("${i}") }
                    if(response1.isSuccessful){
                        response1.body()?.let {res ->
                            res.results?.let { list6.addAll(it) }
                            runOnUiThread{
                                tvOnAirAdapter.notifyDataSetChanged()
                                tv2.visibility = View.VISIBLE
                                pBar.visibility = View.INVISIBLE
                            }
                        }
                        //tv2.visibility = View.VISIBLE
                    }
                }
            }
        }else if (c == "POPULAR TV SHOWS"){
            tv2.text = c
            rView2.apply {
                layoutManager = GridLayoutManager(context,1, RecyclerView.VERTICAL,false)
                adapter = tvPopularAdapter
            }
            tvPopularAdapter.onItemClick = {
                var i = Intent(this,Main7Activity::class.java)

                i.putExtra("id",it.id.toString())

                Toast.makeText(this,"${it.name}",Toast.LENGTH_SHORT).show()

                startActivity(i)
            }
            tv2.visibility = View.INVISIBLE
            GlobalScope.launch {
                for(i in 1..10){
                    val response1 = withContext(Dispatchers.IO){ MovieClient.api.getAllPopularTvShows("${i}") }
                    if(response1.isSuccessful){
                        response1.body()?.let {res ->
                            res.results?.let { list7.addAll(it) }
                            runOnUiThread{
                                tvPopularAdapter.notifyDataSetChanged()
                                tv2.visibility = View.VISIBLE
                                pBar.visibility = View.INVISIBLE
                            }
                        }
                        //tv2.visibility = View.VISIBLE
                    }
                }
            }
        }

        if (h=="TV SHOWS"){
            tv2.text = h
            rView2.apply {
                layoutManager = GridLayoutManager(context,1, RecyclerView.VERTICAL,false)
                adapter = discoverTvAdapter
            }
            discoverTvAdapter.onItemClick = {
                var i = Intent(this,Main7Activity::class.java)

                i.putExtra("id",it.id.toString())

                Toast.makeText(this,"${it.name}",Toast.LENGTH_SHORT).show()

                startActivity(i)
            }
            tv2.visibility = View.INVISIBLE
            GlobalScope.launch {
                for(i in 1..10){
                    val response1 = withContext(Dispatchers.IO){ MovieClient.api.getAllDiscoveredTvShows("${i}") }
                    if(response1.isSuccessful){
                        response1.body()?.let {res ->
                            res.results?.let { list8.addAll(it) }
                            runOnUiThread{
                                discoverTvAdapter.notifyDataSetChanged()
                                pBar.visibility = View.INVISIBLE
                                tv2.visibility = View.VISIBLE
                            }
                        }
                        //tv2.visibility = View.VISIBLE
                    }
                }
            }
        }
        if (h == "MOVIES"){
            tv2.text = h
            rView2.apply {
                layoutManager = GridLayoutManager(context,1, RecyclerView.VERTICAL,false)
                adapter = discoverMovieAdapter
            }
            discoverMovieAdapter.onItemClick = {
                val i = Intent(this,Main6Activity::class.java)

                i.putExtra("id",it.id.toString())

                startActivity(i)
            }
            tv2.visibility = View.INVISIBLE
            GlobalScope.launch {
                for(i in 1..10){
                    val response1 = withContext(Dispatchers.IO){ MovieClient.api.getAllDiscoveredMovies("${i}") }
                    if(response1.isSuccessful){
                        response1.body()?.let {res ->
                            res.results?.let { list9.addAll(it) }
                            runOnUiThread{
                                discoverMovieAdapter.notifyDataSetChanged()
                                tv2.visibility = View.VISIBLE
                                pBar.visibility = View.INVISIBLE
                            }
                        }
                        //tv2.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}
