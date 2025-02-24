package com.geeks.example.familystatus.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Notes")
data class ScheduleDto(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "desc")
    val desc: String,
): Serializable
