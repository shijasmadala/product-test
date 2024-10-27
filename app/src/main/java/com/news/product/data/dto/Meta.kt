package com.news.product.data.dto


import android.os.Parcelable
import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Keep
@JsonClass(generateAdapter = true)
@Parcelize
data class Meta(
    @Json(name = "barcode")
    val barcode: String?,
    @Json(name = "createdAt")
    val createdAt: String?,
    @Json(name = "qrCode")
    val qrCode: String?,
    @Json(name = "updatedAt")
    val updatedAt: String?
) : Parcelable