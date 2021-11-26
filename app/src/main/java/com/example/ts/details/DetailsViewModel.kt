package com.example.ts.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ts.api.ApiClient
import com.example.ts.db.LibraryData
import kotlinx.coroutines.*

private val TAG: String = DetailsViewModel::class.java.simpleName

class DetailsViewModel : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val content = MutableLiveData<LibraryData>()
    private var job: Job? = null

    private val loading = MutableLiveData<Boolean>()

    fun getContentDetails(id: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = DetailsRepository(ApiClient.apiService).getContentDetails(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    content.value = response.body()?.item
                    Log.i(TAG, response.body()?.item.toString())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}