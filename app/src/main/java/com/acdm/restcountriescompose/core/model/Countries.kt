package com.acdm.restcountriescompose.core.model

import com.acdm.restcountriescompose.core.database.entity.CountriesFromApiEntity

data class Countries(
    val name: NameCountries,
    val currencies: CurrenciesCountries,
    val capital: String,
    val region: String,
    val subregion: String,
    val languages: LanguagesCountries,
    val flags: FlagsCountries,
)

fun Countries.toDomainChaAttFromApi() = CountriesFromApiEntity(name,currencies, capital ,region,subregion,languages,flags)
