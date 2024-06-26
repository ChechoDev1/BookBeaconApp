package com.example.projectbookbeaconapp

import com.google.gson.annotations.SerializedName

data class BookRecommendation(
    @SerializedName("Author") val author: String,
    @SerializedName("Title") val title: String,
    @SerializedName("genres") val genres: String
)