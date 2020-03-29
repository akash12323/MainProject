package com.example.mainproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproject.Client.MovieClient
import com.example.mainproject.adapter.SearchAdapter
import com.example.mainproject.adapter.TrendingAdapter
import com.example.mainproject.model.multisearch.ResultsSearch
import com.example.mainproject.model.trending.TrendingResultsItem
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

    val list1 = arrayListOf<TrendingResultsItem>()
    val trendingadapter = TrendingAdapter(list1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(toolbar1)

        val toogle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar1,
            R.string.open,
            R.string.close
        )

        drawer.addDrawerListener(toogle)
        toogle.syncState()

        navView.setNavigationItemSelectedListener(this)

        ParentRView.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity,RecyclerView.VERTICAL,false)
            adapter = trendingadapter
        }
        trendingadapter.onItemClick = {
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
            val response = withContext(Dispatchers.IO){ MovieClient.api.getTrendings() }

            if (response.isSuccessful){
                response.body()?.let {res->
                    res.results?.let { Log.i("xxy",it.toString())
                        list1.addAll(it)}
                    runOnUiThread{ trendingadapter.notifyDataSetChanged() }
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu3,menu)

        val item = menu?.findItem(R.id.search)
        val searchView = item?.actionView as SearchView
        searchView.setQueryHint("type here to search")

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                displayTodo(query.toString())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(!newText.isNullOrEmpty()){
                    displayTodo(newText.toString())
                }
                else if (newText.isNullOrEmpty()){
                    displayTodo(newText.toString())
                }
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

     fun displayTodo(keyword:String){
        if (keyword.length>0){
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
                val response = withContext(Dispatchers.IO){ MovieClient.api.getMultiSeach(keyword.toString(),"1") }

                if (response.isSuccessful){
                    response.body()?.let {res->
                        res.results?.let { Log.i("xxy",it.toString())
                            list1.clear()
                            list.clear()
                            list.addAll(it)}
                        runOnUiThread{ searchadapter.notifyDataSetChanged() }
                    }
                }
            }
        }
         else{
            ParentRView.apply {
                layoutManager = LinearLayoutManager(this@SearchActivity,RecyclerView.VERTICAL,false)
                adapter = trendingadapter
            }
            trendingadapter.onItemClick = {
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
                val response = withContext(Dispatchers.IO){ MovieClient.api.getTrendings() }

                if (response.isSuccessful){
                    response.body()?.let {res->
                        res.results?.let { Log.i("xxy",it.toString())
                            list1.addAll(it)}
                        runOnUiThread{ trendingadapter.notifyDataSetChanged() }
                    }
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.search){
            Toast.makeText(this,"search clicked",Toast.LENGTH_SHORT).show()
        }
        return true
    }
}


//<com.google.android.material.textfield.TextInputLayout
//android:layout_width="match_parent"
//android:layout_height="wrap_content"
//android:id="@+id/see"
//style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox"
//app:boxCornerRadiusTopStart="15dp"
//app:boxCornerRadiusTopEnd="15dp"
//app:boxCornerRadiusBottomStart="15dp"
//app:boxCornerRadiusBottomEnd="15dp"
//android:layout_margin="16dp"
//app:boxStrokeColor="@android:color/holo_orange_dark">
//
//<com.google.android.material.textfield.TextInputEditText
//android:layout_width="match_parent"
//android:layout_height="wrap_content"
//android:cursorVisible="true"
//android:drawablePadding="8dp"
//android:background="@android:color/background_light"
//android:drawableEnd="@drawable/ic_search"
//android:drawableTint="@android:color/holo_orange_light"
//android:clickable="true"
//android:focusable="true"
//android:textColor="@android:color/background_dark"
//android:id="@+id/sea"/>
//
//</com.google.android.material.textfield.TextInputLayout>
//
//<Button
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:id="@+id/sbtn"
//android:text="search"
//android:backgroundTint="@android:color/holo_orange_dark"
//android:layout_below="@id/see"
//android:layout_marginStart="20dp"
///>