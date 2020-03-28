package com.example.mainproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.mainproject.Client.MovieClient
import com.example.mainproject.adapter.movieadapter.MovieCastAdapter
import com.example.mainproject.model.Wishes
import com.example.mainproject.model.Wishesmovies
import com.example.mainproject.model.modelmovies.credits.CastItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main6.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Main6Activity : AppCompatActivity() {

    val list = arrayListOf<CastItem>()
    val moviecastadapter = MovieCastAdapter(list)

    val db by lazy {
        Room.databaseBuilder(this,
            AppDatabase::class.java,
            "app.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    var trigger = MutableLiveData<Boolean>()

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        val movie_id = intent.getStringExtra("id")

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO){MovieClient.api.getMovieTop("${movie_id}")}

            if (response.isSuccessful){
                response.body()?.let {
                    runOnUiThread {
                        tv1.text = it.title+"( ${it.originalTitle} )"
                        tv4.text = it.overview
                        tv3.text = "Release Date: "+it.releaseDate
                        ratings.text = it.popularity.toString()
                        Picasso.get().load("https://image.tmdb.org/t/p/w500"+"${it.posterPath.toString()}")
                            .into(imgView)
                        Picasso.get().load("https://image.tmdb.org/t/p/w500"+"${it.posterPath.toString()}")
                            .into(cirImgiew)
                    }
                }
            }
        }

        cast.apply {
            layoutManager = LinearLayoutManager(this@Main6Activity,RecyclerView.HORIZONTAL,false)
            adapter = moviecastadapter
        }

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO){ MovieClient.api.getMoviesCast(movie_id) }

            if (response.isSuccessful){
                response.body()?.let { res->
                    res.cast?.let { list.addAll(it) }
                    runOnUiThread { moviecastadapter.notifyDataSetChanged() }
                }
            }
        }

        btn.setOnClickListener {
            val i = Intent(this,Main10Activity::class.java)
            i.putExtra("movieid",movie_id)
            startActivity(i)
        }

        var lists = arrayListOf<Long>()

        add.setOnClickListener {
            val i = Intent(this,WishlistMovie::class.java)

            trigger.value = false

            GlobalScope.launch {
                val response = withContext(Dispatchers.IO){MovieClient.api.getMovieTop(movie_id)}

                if (response.isSuccessful){
                    response.body()?.let {
                        GlobalScope.launch(Dispatchers.Main) {
                            val a = withContext(Dispatchers.IO){
                                lists = db.wishesdao().getAllUsersMoviesId() as ArrayList<Long>
                                if (lists.contains(movie_id.toLong())){
                                    GlobalScope.launch(Dispatchers.Main) {
                                        Toast.makeText(this@Main6Activity,"Present in the wishlist",Toast.LENGTH_SHORT).show()
                                    }
                                }
                                else{
                                    db.wishesdao().insertMovies(
                                        Wishesmovies(it.title.toString(),it.originalTitle.toString(),
                                            it.posterPath.toString(),it.overview.toString(), it.id?.toLong())
                                    )
                                    GlobalScope.launch(Dispatchers.Main) {
                                        Toast.makeText(this@Main6Activity,"Added To Wishlist", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }
                    }
                }
            }
            i.putExtra("iid",movie_id)
        }

        remove.setOnClickListener {
            add.visibility = View.VISIBLE
            remove.visibility = View.INVISIBLE
            Toast.makeText(this,"Removed From Wishlist",Toast.LENGTH_SHORT).show()
        }

    }
}