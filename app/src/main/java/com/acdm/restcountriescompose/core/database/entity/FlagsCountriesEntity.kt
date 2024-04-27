package com.acdm.restcountriescompose.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FlagsCountriesEntity")
data class FlagsCountriesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val png: String,
    val svg: String,
    val alt: String
)
