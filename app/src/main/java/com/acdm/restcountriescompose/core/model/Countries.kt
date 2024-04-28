package com.acdm.restcountriescompose.core.model

import com.acdm.restcountriescompose.core.database.entity.CountriesFromApiEntity

data class Countries(
    val name: NameCountries,
    val capital: List<String>,
    val region: String,
    val subregion: String,
    //val languages: HashMap<String, String>,
    val flags: FlagsCountries,
)

fun Countries.toDomainChaAttFromApi() = CountriesFromApiEntity(
    name = name.common,
    nameOfficial = name.official,
    capital = capital[0],
    region = region,
    subregion = subregion,
    flags = flags.png
)
