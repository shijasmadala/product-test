package com.news.product.data.dto


import android.os.Parcelable
import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Keep
@JsonClass(generateAdapter = true)
@Parcelize
data class Review(
    @Json(name = "comment")
    val comment: String?,
    @Json(name = "date")
    val date: String?,
    @Json(name = "rating")
    val rating: Int?,
    @Json(name = "reviewerEmail")
    val reviewerEmail: String?,
    @Json(name = "reviewerName")
    val reviewerName: String?
) : Parcelable