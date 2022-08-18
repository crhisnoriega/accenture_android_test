package com.imdb.feature.listing.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieListResponse(
    @SerializedName("items") val items: List<MovieData>? = null
) : Parcelable