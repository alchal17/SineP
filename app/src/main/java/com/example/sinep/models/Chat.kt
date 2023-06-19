package com.example.sinep.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Chat(val users: Set<@RawValue User>, val messages: MutableList<@RawValue Message>): Parcelable