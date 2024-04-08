package com.geeks.example.familystatus.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.geeks.example.familystatus.data.model.ScheduleDto

@Dao
interface ScheduleDao {
    @Insert
    fun insertNotes(notes: ScheduleDto)

    @Query("SELECT * FROM Notes")
    fun getAllNotes() : List<ScheduleDto>
}