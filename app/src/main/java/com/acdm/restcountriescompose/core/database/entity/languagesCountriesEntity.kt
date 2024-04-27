package com.acdm.restcountriescompose.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "languagesCountriesEntity" )
data class languagesCountriesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val ron: String
)