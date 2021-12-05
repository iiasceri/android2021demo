package com.example.ts.library

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ts.api.ApiClient
import com.example.ts.db.LibraryData
import com.example.ts.db.RoomLibraryRepository
import com.example.ts.db.TblLibrary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(val roomLibraryRepository: RoomLibraryRepository) :
    ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val libraryList = MutableLiveData<List<LibraryData>>()
    var job: Job? = null

    fun getAllContents() {
        job = CoroutineScope(Dispatchers.IO).launch {

            val list = roomLibraryRepository.getLibraryData()
            if (list.isNotEmpty())
                libraryList.postValue(list)
            else {
                val response = LibraryRepository(ApiClient.apiService).getAllContents()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val values = response.body()?.items
                        libraryList.postValue(values)
                    } else {
                        onError("Error : ${response.message()} ")
                        Log.e("error : ", response.message())
                    }
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