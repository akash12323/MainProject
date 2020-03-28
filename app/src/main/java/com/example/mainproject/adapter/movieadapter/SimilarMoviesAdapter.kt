package com.example.mainproject.adapter.movieadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproject.R
import com.example.mainproject.model.modelmovies.similarmovies.ResultsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.simiar.view.*

class SimilarMoviesAdapter(val user:List<ResultsItem>):
    RecyclerView.Adapter<SimilarMoviesAdapter.ItemVieHolder>(){

    var onItemClick:((user: ResultsItem)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVieHolder {
        return ItemVieHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.simiar,
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
        fun bind(user: ResultsItem){
            itemView.apply {
                tvf1.text = user.title
                tv2.text = user.overview

                Picasso.get().load("https://image.tmdb.org/t/p/w500"+user.posterPath.toString()).into(simImg)
                setOnClickListener{
                    onItemClick?.invoke(user)
                }
            }
        }
    }
}