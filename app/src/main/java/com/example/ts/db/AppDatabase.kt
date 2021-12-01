package com.example.ts.db

import android.content.Context
import androidx.room.Database
import androidx.room.InvalidationTracker
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Class to define the database used by the application
 */
@Database(
    entities = arrayOf(
        LibraryData::class
    ),
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    // Add the tables in the database here
    abstract fun libraryDataDao(): LibraryDataDao

    /**
     * Returns the invalidation tracker for this database.
     *
     *
     * You can use the invalidation tracker to get notified when certain tables in the database
     * are modified.
     *
     * @return The invalidation tracker for the database.
     */
    override fun getInvalidationTracker(): InvalidationTracker {
        return super.getInvalidationTracker()
    }

    /**
     * Called when the RoomDatabase is created.
     *
     *
     * This is already implemented by the generated code.
     *
     * @return Creates a new InvalidationTracker.
     */
    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("not implemented")
    }

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "app.db"
                    )
                        .allowMainThreadQueries()   // TODO: should NOT be used in production
//                        .addMigrations(MIGRATION_0_1)
                        .build()
                }
            }
            if (INSTANCE == null)
                throw Exception("Error creating database!!!")
            else
                return INSTANCE!!
        }

        @Suppress("unused")
        fun destroyInstance() {
            INSTANCE = null
        }
    }


}


