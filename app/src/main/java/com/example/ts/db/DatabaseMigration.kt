package com.gtl.android.gtapp.appspecific


import android.util.Log
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

// This needs to be incremented if database schema changes, a migration callback must also be added
const val DATABASE_VERSION = 7

private fun runSql(database: SupportSQLiteDatabase, migration: String, tableName: String, sqlQuery: String): Int {
    return try {
        Log.d(migration, "Upgrading table $tableName")
        database.execSQL(sqlQuery)
        0
    } catch (e: Exception) {
        Log.e(migration, "Database upgrade failed for $tableName: $e")
        1
    }
}

private fun columnExists(database: SupportSQLiteDatabase, tableName: String, columnName: String): Boolean {
    var isExist = false
    // "PRAGMA table_info" returns the details of all the columns in the table
    val res = database.query("PRAGMA table_info($tableName)", null)
    res.moveToFirst()
    do {
        val currentColumn = res.getString(1)
        if (currentColumn == columnName) {
            isExist = true
        }
    } while (res.moveToNext())
    return isExist
}

val MIGRATION_0_1: Migration = object : Migration(0, 1) {
    /**
     * Should run the necessary migrations.
     *
     * This class cannot access any generated Dao in this method.
     *
     * This method is already called inside a transaction and that transaction might actually be a
     * composite transaction of all necessary `Migration`s.
     *
     * @param database The database instance
     */
    override fun migrate(database: SupportSQLiteDatabase) {
        val errorCount = 0
        var sql: String
        val migration = "MIGRATION_0_1"

        Log.d(migration, "Upgrade completed with $errorCount error(s)")
    }
}