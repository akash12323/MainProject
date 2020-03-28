package com.example.mainproject.adapter.movieadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproject.R
import com.example.mainproject.model.modelmovies.MoviesNowPlaying
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.child_view.view.*

class NowPlayingChildAdapter(val user:List<MoviesNowPlaying>):
    RecyclerView.Adapter<NowPlayingChildAdapter.ItemVieHolder>(){

    var onItemClick:((user: MoviesNowPlaying)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVieHolder {
        return ItemVieHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.child_view,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = user.size

    override fun onBindViewHolder(holder: ItemVieHolder, position: Int) {
        holder.bind(user[position])
    }
    inner class ItemVieHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(user: MoviesNowPlaying){
            itemView.apply {
                tv_child1.text = user.title
                tv_child2.text = user.releaseDate

                Picasso.get().load("https://image.tmdb.org/t/p/w500"+user.posterPath.toString()).into(imageView)
                setOnClickListener{
                    onItemClick?.invoke(user)
                }
            }
        }
    }

}
