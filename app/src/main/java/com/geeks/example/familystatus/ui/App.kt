package com.geeks.example.familystatus.ui

import android.app.Application
import androidx.room.Room
import com.geeks.example.familystatus.data.dataBase.MainDb

class App : Application() {
    companion object {
        lateinit var db : MainDb
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            this, MainDb::class.java,
            "roomAndroid"
        ).allowMainThreadQueries().build()
    }
}