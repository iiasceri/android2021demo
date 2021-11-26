package com.example.ts.db

import android.content.Context
import android.util.Log
import androidx.annotation.NonNull
import androidx.room.*

/**
 * Class to map to database table tblLibrary.
 * Note the @Ignore constructor below is just to stop Java warnings
 */
@Entity(tableName = "tblLibrary")
data class LibraryData @Ignore constructor(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id", index = true)
    @NonNull var id: Int,

    @ColumnInfo(name = "Title", index = true)
    var title: String,

    @ColumnInfo(name = "Subtitle", index = true)
    var subtitle: String,

    @ColumnInfo(name = "Body", index = true)
    var body: String,

    @ColumnInfo(name = "Date")
    @NonNull var date: String

) {
    /**
     * This is the default constructor
     */
    constructor() : this(0, "", "", "", "")
}

//**************************************************************************************************

/**
 * Class for SQL queries on database table
 */
@Dao
interface LibraryDataDao {

    @Query("SELECT * from tblLibrary ORDER BY Date DESC")
    fun getAll(): List<LibraryData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(libraryData: LibraryData)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(libraryData: LibraryData)

    @Query("DELETE from tblLibrary")
    fun deleteAll()


    @Query("SELECT COUNT (*) FROM tblLibrary")
    fun count(): Int
}

class TblLibrary private constructor(context: Context) {
    // Properties
    private val _className = javaClass.name.substringAfterLast('.')
    private var libraryDataDao: LibraryDataDao? = null

    init {
        val db = AppDatabase.getInstance(context)
        libraryDataDao = db.libraryDataDao()
        Log.i(_className, "init{} tblLibrary initialised...")
    }

    // Container for static functions
    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        private val _className = javaClass.name.substringAfterLast('.')
        private var _instance: TblLibrary? = null

        /**
         * Public method to get single instance of table, creates if not already done
         */
        fun getInstance(context: Context): TblLibrary {
            // Create the single instance if it does not exist
            if (_instance == null) {
                synchronized(TblLibrary::class) {
                    _instance = TblLibrary(context)
                }
            }

            // Return the single instance
            if (_instance == null) {
                Log.e(_className, "getInstance() Error accessing TblLibrary")
                throw Exception("Error accessing TblLibrary!!!")
            } else {
                return _instance!!
            }
        }

        /**
         * Public method to destroy the single instance of TblLibraryData
         */
        @Suppress("unused")
        fun destroyInstance() {
            _instance = null
        }
    }

    /**
     * Method to get all records in table
     */
    fun getAll(): List<LibraryData>? {
        return libraryDataDao?.getAll()
    }

    /**
     * Method to delete all records in TblLibraryData
     */
    fun deleteAll() {
        libraryDataDao?.deleteAll()
    }
    
    /**
     * Method to count all records in TblLibraryData
     */
    fun count(): Int {
        return libraryDataDao!!.count()
    }

    /**
     * Method to add a record to the table tblFaceRecSpoofData
     */
    fun insert(libraryData: LibraryData) {
        synchronized(TblLibrary::class) {
            libraryDataDao?.insert(libraryData)
        }
    }

}