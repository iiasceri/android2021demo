package com.example.ts.library

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ts.api.ApiClient
import com.example.ts.db.LibraryData
import kotlinx.coroutines.*

class LibraryViewModel : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val libraryList = MutableLiveData<List<LibraryData>>()
    var job: Job? = null

    fun getAllContents() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response =  LibraryRepository(ApiClient.apiService).getAllContents()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    libraryList.postValue(response.body()?.items)
                } else {
                    onError("Error : ${response.message()} ")
                    Log.e("error : ", response.message())
                }
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}