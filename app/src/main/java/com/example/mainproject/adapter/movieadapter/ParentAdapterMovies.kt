package com.example.mainproject.adapter.movieadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproject.model.modelmovies.ParentMovies
import com.example.mainproject.R
import com.example.mainproject.model.modelmovies.MoviesTopRated
import kotlinx.android.synthetic.main.parent_view.view.*

class ParentAdapterMovies(private val parents : List<ParentMovies>) :
    RecyclerView.Adapter<ParentAdapterMovies.ViewHolder>(){

    var onItemClick:((user: ParentMovies)->Unit)? = null
    var onTopMovieClick:((List<MoviesTopRated>)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.parent_view,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return parents.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(parents[position])
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(user: ParentMovies){
            itemView.apply {
                tv_parent.text = user.category

                if (user.category == "TOP-RATED MOVIES"){
                    ChildRView.apply {
                        layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
                        adapter = TopRatedChildAdapter(user.topRated)
                    }
                    TopRatedChildAdapter(user.topRated).onItemClick={
                        Toast.makeText(context,"Top Rated Movies",Toast.LENGTH_SHORT).show()
                    }
                }
                else if (user.category == "UPCOMING MOVIES"){
                    ChildRView.apply {
                        layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
                        adapter = UpcomingChildAdapter(user.upcoming)
                    }
                }
                else if (user.category == "POPULAR MOVIES"){
                    ChildRView.apply {
                        layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
                        adapter = PopularChildAdapter(user.popular)
                    }
                }
                else{
                    ChildRView.apply {
                        layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
                        adapter = NowPlayingChildAdapter(user.nowPlaying)
                    }
                }
                setOnClickListener{
                    onItemClick?.invoke(user)
                    onTopMovieClick?.invoke(user.topRated)
                }
            }
        }
    }


}
