package com.example.ts.models

import com.example.ts.db.LibraryData

data class LibraryList (
    val items: MutableList<LibraryData>? = null
)