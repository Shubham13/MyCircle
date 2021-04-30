package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.CirclesItem
import com.example.myapplication.R

class CircleAdapter(var circles: ArrayList<CirclesItem>?) : RecyclerView.Adapter<CircleAdapter.CircleHolder>() {

    var circlList: ArrayList<CirclesItem>? = circles

    class CircleHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var row_text = itemView.findViewById<TextView>(R.id.row_text)
        var star_icon = itemView.findViewById<ImageView>(R.id.star_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CircleHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_circle,parent,false)
        return CircleHolder(itemView)
    }

    override fun onBindViewHolder(holder: CircleHolder, position: Int) {
        holder.row_text.text = circlList?.get(position)?.name

        if(circlList?.get(position)?.isSelect == true){
            holder.star_icon.visibility = View.VISIBLE
        }else{
            holder.star_icon.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return circlList?.size!!
    }

    fun setCircle(circleList: ArrayList<CirclesItem>){
        this.circlList = circleList
        notifyDataSetChanged()
    }
}