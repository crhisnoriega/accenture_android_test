package com.imdb.feature.listing.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MovieData(
    @SerializedName("id") val id: String? = null,
    @SerializedName("rank") val rank: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("fullTitle") val fullTitle: String? = null,
    @SerializedName("image") val imageUrl: String? = null,
) : Parcelable