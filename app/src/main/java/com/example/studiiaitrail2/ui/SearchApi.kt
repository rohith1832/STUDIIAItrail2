package com.example.studiiaitrail2.ui

import com.example.studiiaitrail2.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("customsearch/v1")
    suspend fun search(
        @Query("key") apiKey: String,
        @Query("cx") cx: String,
        @Query("q") query: String
    ): SearchResponse
}
