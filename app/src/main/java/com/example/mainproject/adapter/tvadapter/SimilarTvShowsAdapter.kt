package com.example.mainproject.adapter.tvadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproject.R
import com.example.mainproject.model.model_tv.similartvshows.SimilarTv
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.simiar.view.*

class SimilarTvShowsAdapter(val user:List<SimilarTv>):
    RecyclerView.Adapter<SimilarTvShowsAdapter.ItemVieHolder>(){

    var onItemClick:((user: SimilarTv)->Unit)? = null

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
        fun bind(user: SimilarTv){
            itemView.apply {
                tvf1.text = user.name
                tv2.text = user.overview

                Picasso.get().load("https://image.tmdb.org/t/p/w500"+user.posterPath.toString()).into(simImg)
                setOnClickListener{
                    onItemClick?.invoke(user)
                }
            }
        }
    }
}