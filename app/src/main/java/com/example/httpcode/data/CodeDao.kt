package com.example.httpcode.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CodeDao {

    @Query("SELECT * FROM code_table ORDER BY code ASC")
    fun getAllCodes(): List<Code>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(code: Code)

    @Query("DELETE FROM code_table")
    suspend fun deleteAll()
}