package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.listner.OnItemClickListner
import com.example.myapplication.R
import com.example.myapplication.model.UsersItem

class UserAdapter(var allUsers: ArrayList<UsersItem?>) : RecyclerView.Adapter<UserAdapter.UserHolder>() {

    var userList : ArrayList<UsersItem?> = allUsers
    lateinit var clickListner : OnItemClickListner
    var rowPosition : Int = -1

    fun setOnClickListner(onItemClickListner: OnItemClickListner){
        this.clickListner = onItemClickListner
    }

    class UserHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var row_ll = itemView.findViewById<LinearLayout>(R.id.row_ll)
        var row_text = itemView.findViewById<TextView>(R.id.row_text)
        var star_icon = itemView.findViewById<ImageView>(R.id.star_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_circle,parent,false)
        return UserHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.row_text.text = userList[position]?.name

        if(rowPosition == position){
            holder.star_icon.visibility = View.VISIBLE
        }else{
            holder.star_icon.visibility = View.GONE
        }

        holder.row_ll.setOnClickListener {
            clickListner.onItemClickListener(userList.get(position),position)
        }
    }

    fun setPosition(position: Int){
        this.rowPosition = position
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}