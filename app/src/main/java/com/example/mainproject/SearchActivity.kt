package com.example.mainproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproject.Client.MovieClient
import com.example.mainproject.adapter.SearchAdapter
import com.example.mainproject.model.multisearch.ResultsSearch
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.main_include.*
import kotlinx.android.synthetic.main.main_include2.*
import kotlinx.android.synthetic.main.main_include2.ParentRView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val list = arrayListOf<ResultsSearch>()
    val searchadapter = SearchAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

//        setSupportActionBar(toolbar)

        val toogle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.open,
            R.string.close
        )

        drawer.addDrawerListener(toogle)
        toogle.syncState()

        navView.setNavigationItemSelectedListener(this)

        ParentRView.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity,RecyclerView.VERTICAL,false)
            adapter = searchadapter
        }
        searchadapter.onItemClick = {
            if (it.mediaType =="movie"){
                val i = Intent(this,Main6Activity::class.java)
                i.putExtra("id",it.id.toString())
                startActivity(i)
            }
            else if (it.mediaType == "tv"){
                val i = Intent(this,Main7Activity::class.java)
                i.putExtra("id",it.id.toString())
                startActivity(i)
            }
        }

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO){ MovieClient.api.getMultiSeach("","1") }

            if (response.isSuccessful){
                response.body()?.let {res->
                    res.results?.let { Log.i("xxy",it.toString())
                        list.addAll(it)}
                    runOnUiThread{ searchadapter.notifyDataSetChanged() }
                }
            }
        }

        sbtn.setOnClickListener {
            list.clear()
            for (i in 1..10){
                GlobalScope.launch {
                    val response = withContext(Dispatchers.IO){ MovieClient.api.getMultiSeach(sea.text.toString(),"${i}") }

                    if (response.isSuccessful){
                        response.body()?.let {res->
                            res.results?.let { Log.i("xxx",it.toString())
                                list.addAll(it)}
                            runOnUiThread{ searchadapter.notifyDataSetChanged() }
                        }
                    }
                }
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.movies->{
                Toast.makeText(this,"Movies Pressed", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
            R.id.tv_series->{
                Toast.makeText(this,"TV Series Pressed", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,Main3Activity::class.java))
                finish()
            }
            R.id.people->{
                Toast.makeText(this,"People Pressed", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,Main4Activity::class.java))
                finish()
            }
            R.id.discover->{
                Toast.makeText(this,"Discover Pressed", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,Main5Activity::class.java))
                finish()
            }
            R.id.wishlist->{
                Toast.makeText(this,"WishList Pressed", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,WishListTv::class.java))
                finish()
            }
            R.id.wishlistMovie->{
                Toast.makeText(this,"WishList Pressed", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,WishlistMovie::class.java))
                finish()
            }
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
