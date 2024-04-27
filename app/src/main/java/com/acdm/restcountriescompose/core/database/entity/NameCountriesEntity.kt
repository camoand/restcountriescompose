package com.acdm.restcountriescompose.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NameCountriesEntity")
data class NameCountriesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val common: String,
    val official: String
)
