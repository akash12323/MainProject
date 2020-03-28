package com.example.mainproject.adapter.discoveradapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproject.model.model_discover.ParentDiscover
import com.example.mainproject.R
import kotlinx.android.synthetic.main.parent_view.view.*

class ParentDiscoverAdapter(private val parents : List<ParentDiscover>) :
    RecyclerView.Adapter<ParentDiscoverAdapter.ViewHolder>(){

    var onItemClick:((user: ParentDiscover)->Unit)? = null

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
        fun bind(user: ParentDiscover){
            itemView.apply {
                tv_parent.text = user.heading

                if (tv_parent.text == "TV SHOWS"){
                    ChildRView.apply {
                        layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
                        adapter =
                            DiscoverTvAdapter(
                                user.discoveredTv
                            )
                    }
                }
                else if (tv_parent.text == "MOVIES"){
                    ChildRView.apply {
                        layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
                        adapter =
                            DiscoverMovieAdapter(
                                user.discoveredMovies
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