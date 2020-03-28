package com.example.mainproject.adapter.movieadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproject.R
import com.example.mainproject.model.modelmovies.MoviesPopular
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.child_view.view.*

class PopularChildAdapter(val user:List<MoviesPopular>):
    RecyclerView.Adapter<PopularChildAdapter.ItemVieHolder>(){

    var onItemClick:((user: MoviesPopular)->Unit)? = null

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
        fun bind(user: MoviesPopular){
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