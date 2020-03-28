package com.example.mainproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproject.R
import com.example.mainproject.model.Wishes
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.child_view.view.*

class WishlistAdapter(val user:List<Wishes>):
    RecyclerView.Adapter<WishlistAdapter.ItemVieHolder>(){

    var onItemClick:((user: Wishes)->Unit)? = null

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

    override fun getItemId(position: Int): Long {
        return user[position].id!!
    }
    inner class ItemVieHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(user: Wishes){
            itemView.apply {
                tv_child1.text = user.name+"(${user.original_name})"
                tv_child2.text = user.overview

                Picasso.get().load("https://image.tmdb.org/t/p/w500"+user.posterPath.toString()).into(imageView)
                setOnClickListener{
                    onItemClick?.invoke(user)
                }
            }
        }
    }

}
