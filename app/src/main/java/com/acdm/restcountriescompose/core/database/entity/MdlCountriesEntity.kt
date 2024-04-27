package com.acdm.restcountriescompose.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MdlCountriesEntity")
data class MdlCountriesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val symbol: String
)
