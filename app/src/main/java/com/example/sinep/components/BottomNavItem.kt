package com.example.sinep.components

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon_id: Int,
    var counter: Int = 0
)
