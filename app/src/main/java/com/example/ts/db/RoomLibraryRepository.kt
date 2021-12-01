package com.example.ts.db

import javax.inject.Inject


class RoomLibraryRepository @Inject constructor(private val libraryDataDao: LibraryDataDao) {

    fun getLibraryData(): List<LibraryData> {
        return libraryDataDao.getAll()
    }

    fun insertRecord(libraryData: LibraryData) {
        libraryDataDao.insert(libraryData)
    }
}