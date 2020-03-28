package com.example.mainproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproject.Client.MovieClient
import com.example.mainproject.adapter.tvadapter.SimilarTvShowsAdapter
import com.example.mainproject.model.model_tv.similartvshows.SimilarTv
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_main11.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Main11Activity : AppCompatActivity() {

    val list = arrayListOf<SimilarTv>()
    val similartvadapter = SimilarTvShowsAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main11)

        val tv_id = intent.getStringExtra("tvid")

        similarrView.apply {
            layoutManager = LinearLayoutManager(this@Main11Activity, RecyclerView.VERTICAL,false)
            adapter = similartvadapter
        }

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO){ MovieClient.api.getTvTrailers(tv_id.toString()) }

            if (response.isSuccessful){
                if (response.body()?.results.isNullOrEmpty()){
                    GlobalScope.launch(Dispatchers.Main) {
                        Toast.makeText(this@Main11Activity,"Trailer not available",Toast.LENGTH_LONG).show()
                    }
                }
                else if(tv_id == response.body()?.id.toString()){
                    response.body()?.let {res->
                        res.results?.let { Log.i("asd",it.toString()) }
                        runOnUiThread{
                            lifecycle.addObserver(youtube_player_view)
                            youtube_player_view.addYouTubePlayerListener(object :
                                AbstractYouTubePlayerListener(){
                                override fun onReady(youTubePlayer: YouTubePlayer) {
                                    val videoId = res.results?.get(0)?.key.toString()
                                    youTubePlayer.loadVideo(videoId,0F)
                                    super.onReady(youTubePlayer)
                                }
                            })
                            tv1.text = "Similar Tv Shows "
                        }
                    }
                }
            }
        }
        similartvadapter.onItemClick={
            val i = Intent(this,Main13Activity::class.java)
            i.putExtra("ids",it.id)
            startActivity(i)
            Toast.makeText(this,it.name+"\n"+it.id,Toast.LENGTH_SHORT).show()
        }

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO){ MovieClient.api.getSimilarTvShows(tv_id.toString()) }
            if (response.isSuccessful){
                response.body()?.let {res->
                    res.results?.let { list.addAll(it)
                        Log.i("asf",it.toString())
                    }
                    runOnUiThread{ similartvadapter.notifyDataSetChanged() }
                }
            }
        }

    }
}
