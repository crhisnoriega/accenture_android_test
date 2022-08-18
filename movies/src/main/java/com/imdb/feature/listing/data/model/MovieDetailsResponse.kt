package com.imdb.feature.listing.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MovieDetailsResponse(
    @SerializedName("id") val id: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("originalTitle") val originalTitle: String? = null,
    @SerializedName("plot") val plot: String? = null,
    @SerializedName("image") val imageUrl: String? = null,
) : Parcelable