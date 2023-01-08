package com.example.applaudoscodechallengeandroid.data.remote.dto


import com.google.gson.annotations.SerializedName

data class PageResultDto<T>(
    val page: Int,
    val results: List<T>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)