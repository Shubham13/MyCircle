package com.example.myapplication.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.Utill
import com.example.myapplication.adapter.CircleAdapter
import com.example.myapplication.adapter.UserAdapter
import com.example.myapplication.listner.OnItemClickListner
import com.example.myapplication.model.Circles
import com.example.myapplication.model.CirclesItem
import com.example.myapplication.model.UsersItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var circle : Circles
    var userAdapter : UserAdapter? = null
    var circleAdapter : CircleAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        circle = Utill.deserialize(Utill.getJsonDataFromAsset(this, "circles.json"), Circles::class.java)

        user_rv.layoutManager = LinearLayoutManager(this)
        circle_rv.layoutManager = LinearLayoutManager(this)

        userAdapter = UserAdapter(getAllUsers())
        circleAdapter = CircleAdapter(circle?.circles)

        user_rv.adapter = userAdapter
        circle_rv.adapter = circleAdapter

        userAdapter!!.setOnClickListner(object : OnItemClickListner {
            override fun onItemClickListener(value: Any?, position : Int) {
                var userItem : UsersItem = value as UsersItem
                checkCircleForUser(userItem)
                userAdapter?.setPosition(position)
            }
        })
    }

    private fun checkCircleForUser(userItem: UsersItem) {
        var userCircle : ArrayList<CirclesItem> = ArrayList()

        for(i in circle.circles?.indices!!){

            for(j in 0..(circle.circles!!.get(i).users?.size?.minus(1) ?: 0)){

                if(userItem.id.equals(circle.circles!!.get(i).users?.get(j)?.id)){
                    userCircle.add(circle.circles!![i])
                    break
                }

            }
        }

        if(userCircle.size>0) {
            for (r in 0..userCircle.size - 1) {
                var circlesItem = userCircle[r]
                circlesItem.isSelect = true
            }
        }

        circleAdapter?.setCircle(userCircle)
    }

    private fun getAllUsers() : ArrayList<UsersItem?> {
        var userSet : LinkedHashSet<UsersItem?> = LinkedHashSet()
        var userList : ArrayList<UsersItem?> = ArrayList()

        for(i in 0..(circle.circles?.size?.minus(1) ?: 0)){

            for (j in 0..(circle.circles?.get(i)?.users?.size?.minus(1) ?: 0)){

                var usersItem : UsersItem? = circle.circles?.get(i)?.users?.get(j)
                userSet.add(usersItem)
                Log.e("size",""+userSet.size)
            }
        }
        userList.addAll(userSet)
        return userList
    }
}