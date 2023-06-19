package com.example.sinep.models

import kotlinx.parcelize.RawValue


data class User(
    val nickname: String,
    val profilePicture: Int,
    val bio: String = ""
)
