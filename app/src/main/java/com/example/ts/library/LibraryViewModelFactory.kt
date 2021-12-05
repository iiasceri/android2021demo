package com.example.ts.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ts.db.RoomLibraryRepository

class LibraryViewModelFactory(val roomLibraryRepository: RoomLibraryRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LibraryViewModel(roomLibraryRepository) as T
    }
}