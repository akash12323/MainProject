package com.example.mainproject.adapter.tvadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproject.R
import com.example.mainproject.model.model_tv.TvOnAir
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.child_view.view.*

class TvOnAirAdapter(val user:List<TvOnAir>):
    RecyclerView.Adapter<TvOnAirAdapter.ItemVieHolder>(){

    var onItemClick:((user: TvOnAir)->Unit)? = null

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
        fun bind(user: TvOnAir){
            itemView.apply {
                tv_child1.text = user.name
                tv_child2.text = user.firstAirDate

//                userImage.shapeAppearanceModel = userImage.shapeAppearanceModel.toBuilder()
//                    .setBottomLeftCorner(CornerFamily.CUT,100f)
//                    .setBottomRightCorner(CornerFamily.CUT,100f)
//                    .setTopLeftCorner(CornerFamily.ROUNDED,100f)
//                    .setTopRightCorner(CornerFamily.ROUNDED,100f)
//                    .build()

                Picasso.get().load("https://image.tmdb.org/t/p/w500"+user.posterPath.toString()).into(imageView)
                setOnClickListener{
                    onItemClick?.invoke(user)
                }
            }
        }
    }


}