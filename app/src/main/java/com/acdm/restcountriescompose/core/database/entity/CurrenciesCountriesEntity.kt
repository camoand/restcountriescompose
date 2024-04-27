package com.acdm.restcountriescompose.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.acdm.restcountriescompose.core.model.MdlCountries

@Entity(tableName = "CurrenciesCountriesEntity")
data class CurrenciesCountriesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
   // val MDL: MdlCountries
)
