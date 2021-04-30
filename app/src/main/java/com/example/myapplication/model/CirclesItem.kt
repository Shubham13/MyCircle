package com.example.myapplication.model

data class CirclesItem(val name: String = "",
                       val id: String = "",
                       val users: List<UsersItem>?, var isSelect : Boolean = false)