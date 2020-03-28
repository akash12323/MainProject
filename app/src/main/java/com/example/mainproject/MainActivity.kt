package com.example.mainproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproject.Client.MovieClient
import com.example.mainproject.adapter.SearchAdapter
import com.example.mainproject.adapter.movieadapter.ParentAdapterMovies
import com.example.mainproject.adapter.movieadapter.TopRatedChildAdapter
import com.example.mainproject.model.modelmovies.*
import com.example.mainproject.model.multisearch.ResultsSearch
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_include.*
import kotlinx.android.synthetic.main.main_include.ParentRView
import kotlinx.android.synthetic.main.main_include.toolbar
import kotlinx.android.synthetic.main.main_include2.*
import kotlinx.android.synthetic.main.parent_view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val heading = arrayOf("TOP-RATED MOVIES","POPULAR MOVIES","UPCOMING MOVIES","NOW-PLAYING MOVIES")

    val list = arrayListOf<ParentMovies>()
    val adapter = ParentAdapterMovies(list)

    val list1 = arrayListOf<MoviesTopRated>()

    val list2 = arrayListOf<MoviesNowPlaying>()

    val list3 = arrayListOf<MoviesUpcoming>()

    val list4  = arrayListOf<MoviesPopular>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

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

        for (i in 0..3){
            if (i==0){
                list.add(
                    ParentMovies(
                        heading[i],
                        list1,
                        list3,
                        list4,
                        list2
                    )
                )
            }
            else if (i==1){
                list.add(
                    ParentMovies(
                        heading[i],
                        list1,
                        list3,
                        list4,
                        list2
                    )
                )
            }
            else if (i==2){
                list.add(
                    ParentMovies(
                        heading[i],
                        list1,
                        list3,
                        list4,
                        list2
                    )
                )
            }
            else if (i==3){
                list.add(
                    ParentMovies(
                        heading[i],
                        list1,
                        list3,
                        list4,
                        list2
                    )
                )
            }
        }

        ParentRView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity,RecyclerView.VERTICAL,false)
            adapter = this@MainActivity.adapter
        }
        adapter.onItemClick = {
            val i = Intent(this,Main2Activity::class.java)
            val s= it.category
            i.putExtra("text",s)
            startActivity(i)
        }

        ParentRView.visibility = View.INVISIBLE
        GlobalScope.launch {
            val response1 = withContext(Dispatchers.IO){ MovieClient.api.getAllTopRatedMovie("1",
                "3b64ac412725369ae963817a7514e443") }
            if(response1.isSuccessful){
                response1.body()?.let {res ->
                    res.results?.let { list1.addAll(it) }
                    runOnUiThread{
                        adapter.notifyDataSetChanged()
                        progressBar.visibility = View.INVISIBLE
                        ParentRView.visibility = View.VISIBLE
                    }
                }
            }
        }

        GlobalScope.launch {
            val response1 = withContext(Dispatchers.IO){
                MovieClient.api.getAllRecentMovies("1","2020","3b64ac412725369ae963817a7514e443") }
            if(response1.isSuccessful){
                response1.body()?.let {res ->
                    res.results?.let { list2.addAll(it) }
                    runOnUiThread{
                        adapter.notifyDataSetChanged()
                        progressBar.visibility = View.INVISIBLE
                    }
                }
            }
        }

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO){
                MovieClient.api.getAllPopularMovie("1","3b64ac412725369ae963817a7514e443") }
            if(response.isSuccessful){
                response.body()?.let {res ->
                    res.results?.let { list4.addAll(it) }
                    runOnUiThread{
                        adapter.notifyDataSetChanged()
                        progressBar.visibility = View.INVISIBLE
                    }
                }
            }
        }

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO)
            { MovieClient.api.getAllUpcomingMovies("2020-03-17","1") }
            if(response.isSuccessful){
                response.body()?.let {res ->
                    res.results?.let { list3.addAll(it) }
                    runOnUiThread{
                        adapter.notifyDataSetChanged()
                        progressBar.visibility = View.INVISIBLE
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
                Toast.makeText(this,"People Pressed",Toast.LENGTH_SHORT).show()
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu2, menu)

        val item = menu?.findItem(R.id.search)
        val searchView = item?.actionView as SearchView
        searchView.setQueryHint("type here to search")

        return super.onCreateOptionsMenu(menu)
    }

//        item.setOnActionExpandListener(object : MenuItem.OnActionExpandListener{
//            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
//                displayTodo()
//                return true
//            }
//
//            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
//                displayTodo()
//                return true
//            }
//        })
//
//        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                if(!newText.isNullOrEmpty()){
//                    displayTodo(newText)
//                }
//                return true
//            }
//
//        })
//
//    fun displayTodo(newText:String = ""){
//
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.search){
            Toast.makeText(this,"search clicked",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,SearchActivity::class.java))
        }

        return true
    }

}
