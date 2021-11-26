package com.example.ts.api

import com.example.ts.models.LibraryList
import com.example.ts.models.ItemDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("contentList.json")
    suspend fun getContentList(): Response<LibraryList>

    @GET("content/{id}.json")
    suspend fun getContentById(@Path("id") id : String): Response<ItemDetail>
}