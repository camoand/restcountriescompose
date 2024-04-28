package com.acdm.restcountriescompose.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CountriesFromApiEntity")
data class CountriesFromApiEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val nameOfficial: String,
    val capital: String,
    val region: String,
    val subregion: String,
   // val languages: String,
    val flags: String,
)