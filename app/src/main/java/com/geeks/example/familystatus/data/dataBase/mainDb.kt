package com.geeks.example.familystatus.data.dataBase

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.geeks.example.familystatus.data.model.ScheduleDto
import com.geeks.example.familystatus.data.room.ScheduleDao

@Database (entities = [ScheduleDto::class], version = 1)
abstract class MainDb:RoomDatabase() {
    abstract fun getDao(): ScheduleDao
}