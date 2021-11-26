package com.example.ts.details

import com.example.ts.api.ApiService

class DetailsRepository constructor(private val apiService: ApiService) {

    suspend fun getContentDetails(contentID: String) = apiService.getContentById(contentID )

}