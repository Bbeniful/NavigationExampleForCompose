package com.bbeniful.navigationexample.core.domain.models

import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class CustomModel(
    val name: String = "",
    val someInt: Int = 11
) : Parcelable {
    fun toGson() = Gson().toJson(this)
}