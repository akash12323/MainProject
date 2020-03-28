package com.example.mainproject.adapter.personadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproject.R
import com.example.mainproject.model.model_person.PersonPopular
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.child_view.view.*

class PopularPersonAdapter(val user:List<PersonPopular>):
    RecyclerView.Adapter<PopularPersonAdapter.ItemVieHolder>(){

//    val image = arrayListOf<KnownForItem>()
    var onItemClick:((user: PersonPopular)->Unit)? = null

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
        fun bind(user: PersonPopular){
            itemView.apply {
                tv_child1.text = user.name
                tv_child2.text = user.knownFor?.get(0)?.title

                Picasso.get().load("https://image.tmdb.org/t/p/w500"+ user.profilePath.toString()).into(imageView)
                setOnClickListener{
                    onItemClick?.invoke(user)
                }
            }
        }
    }


}

//user.knownFor?.get(0)?.backdropPath.toString()