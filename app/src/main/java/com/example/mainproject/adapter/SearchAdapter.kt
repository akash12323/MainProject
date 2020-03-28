package com.example.mainproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproject.R
import com.example.mainproject.model.Wishes
import com.example.mainproject.model.multisearch.ResultsSearch
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.child_view.view.*
import kotlinx.android.synthetic.main.child_view.view.imageView
import kotlinx.android.synthetic.main.child_view.view.tv_child1
import kotlinx.android.synthetic.main.child_view.view.tv_child2
import kotlinx.android.synthetic.main.child_view2.view.*

class SearchAdapter(val user:List<ResultsSearch>):
    RecyclerView.Adapter<SearchAdapter.ItemVieHolder>(){

    var onItemClick:((user: ResultsSearch)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVieHolder {
        return ItemVieHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.child_view2,
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
        fun bind(user: ResultsSearch){
            itemView.apply {
                if (user.mediaType == "movie"){
                    tv_child1.text = user.title+"(${user.originalTitle})"
                    tv_child2.text = user.overview
                }
                else if (user.mediaType == "tv"){
                    tv_child1.text = user.name+"(${user.originalName})"
                    tv_child2.text = user.overview
                }
                else if (user.mediaType == "person"){
                    tv_child1.text = user.name
                    tv_child2.text = user.id.toString()
                }

                Picasso.get().load("https://image.tmdb.org/t/p/w500"+user.posterPath.toString()).into(imageView)
                setOnClickListener{
                    onItemClick?.invoke(user)
                }
            }
        }
    }

}