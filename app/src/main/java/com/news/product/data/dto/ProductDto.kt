package com.news.product.data.dto


import android.os.Parcelable
import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Keep
@JsonClass(generateAdapter = true)
@Parcelize
data class ProductDto(
    @Json(name = "limit")
    val limit: Int?,
    @Json(name = "products")
    val products: List<Product?>?,
    @Json(name = "skip")
    val skip: Int?,
    @Json(name = "total")
    val total: Int?
) : Parcelable