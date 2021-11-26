package com.example.ts.library

import com.example.ts.api.ApiService

class LibraryRepository constructor(private val apiService: ApiService) {

    suspend fun getAllContents() = apiService.getContentList()

}