package com.example.sinep.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Post(
    val title: String,
    val description: String,
    val image: Int,
    val user: @RawValue User,
    val NSFW: Boolean = false,
    val like: Int = 0,
    val dislike: Int = 0
): Parcelable