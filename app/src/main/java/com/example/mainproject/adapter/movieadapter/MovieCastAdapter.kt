package com.example.mainproject.adapter.movieadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproject.R
import com.example.mainproject.model.modelmovies.credits.CastItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.child_view3.view.imageView
import kotlinx.android.synthetic.main.child_view3.view.tv_child1
import kotlinx.android.synthetic.main.child_view3.view.tv_child2

class MovieCastAdapter(val user:List<CastItem>):
    RecyclerView.Adapter<MovieCastAdapter.ItemVieHolder>(){

    var onItemClick:((user: CastItem)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVieHolder {
        return ItemVieHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.child_view3,
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
        fun bind(user: CastItem){
            itemView.apply {
                tv_child1.text = user.name
                tv_child2.text = user.character

                Picasso.get().load("https://image.tmdb.org/t/p/w500"+user.profilePath.toString()).into(imageView)
                setOnClickListener{
                    onItemClick?.invoke(user)
                }
            }
        }
    }

}
