package com.example.mainproject.adapter.tvadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproject.model.model_tv.ParentTv
import com.example.mainproject.R
import kotlinx.android.synthetic.main.parent_view.view.*

class ParentAdapterTv(private val parents : List<ParentTv>) :
    RecyclerView.Adapter<ParentAdapterTv.ViewHolder>(){

    var onItemClick:((user: ParentTv)->Unit)? = null

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
        fun bind(user: ParentTv){
            itemView.apply {
                tv_parent.text = user.category

                if (tv_parent.text == "TOP-RATED TV SHOWS"){
                    ChildRView.apply {
                        layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
                        adapter =
                            TvTopRatedAdapter(
                                user.topRatedTv
                            )
                    }
                }
                else if (tv_parent.text == "ON-AIR TV SHOWS"){
                    ChildRView.apply {
                        layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
                        adapter =
                            TvOnAirAdapter(
                                user.onAir
                            )
                    }
                }
                else if (tv_parent.text == "POPULAR TV SHOWS"){
                    ChildRView.apply {
                        layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
                        adapter =
                            TvPopularAdapter(
                                user.popularTv
                            )
                    }
                }
                setOnClickListener{
                    onItemClick?.invoke(user)
                }
            }
        }
    }


}