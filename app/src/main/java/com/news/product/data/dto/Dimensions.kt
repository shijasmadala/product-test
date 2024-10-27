package com.news.product.data.dto


import android.os.Parcelable
import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Keep
@JsonClass(generateAdapter = true)
@Parcelize
data class Dimensions(
    @Json(name = "depth")
    val depth: Double?,
    @Json(name = "height")
    val height: Double?,
    @Json(name = "width")
    val width: Double?
) : Parcelable