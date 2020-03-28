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
import com.example.mainproject.adapter.tvadapter.TvCastAdapter
import com.example.mainproject.model.Wishes
import com.example.mainproject.model.model_tv.cast.CastItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main7.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Main7Activity : AppCompatActivity() {

    val list = arrayListOf<CastItem>()
    val tvCastadapter = TvCastAdapter(list)

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
        setContentView(R.layout.activity_main7)

        val tv_id:String = intent.getStringExtra("id")

        GlobalScope.launch {
            val response =  withContext(Dispatchers.IO){MovieClient.api.getTvTop(tv_id)}

            if(response.isSuccessful){
                response.body()?.let {
                    runOnUiThread { tv1.text = it.name+"( ${it.originalName} )"
                        tv4.text = it.overview
                        tv3.text = "First Air Date: ${it.firstAirDate}"
                        Picasso.get().load("https://image.tmdb.org/t/p/w500"+it.posterPath).into(imgView)
                        Picasso.get().load("https://image.tmdb.org/t/p/w500"+it.posterPath).into(cirImgiew)
                    }
                }
            }
        }

        cast.apply {
            layoutManager = LinearLayoutManager(this@Main7Activity, RecyclerView.HORIZONTAL,false)
            adapter = tvCastadapter
        }

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO){ MovieClient.api.getTvshowCast(tv_id) }

            if (response.isSuccessful){
                response.body()?.let { res->
                    res.cast?.let { list.addAll(it) }
                    runOnUiThread { tvCastadapter.notifyDataSetChanged() }
                }
            }
        }

        btn.setOnClickListener {
            val i = Intent(this,Main11Activity::class.java)
            i.putExtra("tvid",tv_id)
            startActivity(i)
        }

        var lists = arrayListOf<Long>()

        add.setOnClickListener {
            add.visibility = View.INVISIBLE
            add.setBackgroundColor(android.R.color.holo_blue_bright)

            val i = Intent(this,WishListTv::class.java)
            trigger.value = false

            GlobalScope.launch {
                val response = withContext(Dispatchers.IO){MovieClient.api.getTvTop(tv_id)}

                if (response.isSuccessful){
                    response.body()?.let {
                        GlobalScope.launch(Dispatchers.Main) {
                            val a = withContext(Dispatchers.IO){
                                lists = db.wishesdao().getAllUsersTvId() as ArrayList<Long>
                                if (lists.contains(tv_id.toLong())){
                                    GlobalScope.launch(Dispatchers.Main) {
                                        Toast.makeText(this@Main7Activity,"present in wishlist",Toast.LENGTH_SHORT).show()
                                    }
                                }
                                else{
                                    db.wishesdao().insert(Wishes(it.name.toString(),it.originalName.toString(),
                                        it.posterPath.toString(),it.overview.toString(), it.id?.toLong())
                                    )
                                    GlobalScope.launch(Dispatchers.Main) {
                                        Toast.makeText(this@Main7Activity,"Added To Wishlist", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }
                    }
                }
            }

            i.putExtra("id",tv_id)
        }

        remove.setOnClickListener {
            remove.visibility = View.INVISIBLE
            add.visibility = View.VISIBLE
            Toast.makeText(this,"Removed From Wishlist",Toast.LENGTH_SHORT).show()
        }
    }
}
