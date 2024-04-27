package com.acdm.restcountriescompose.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.acdm.restcountriescompose.core.model.CurrenciesCountries
import com.acdm.restcountriescompose.core.model.FlagsCountries
import com.acdm.restcountriescompose.core.model.LanguagesCountries
import com.acdm.restcountriescompose.core.model.NameCountries

@Entity(tableName = "CountriesFromApiEntity")
data class CountriesFromApiEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: NameCountries,
    val currencies: CurrenciesCountries,
    val capital: String,
    val region: String,
    val subregion: String,
    val languages: LanguagesCountries,
    val flags: FlagsCountries,
)