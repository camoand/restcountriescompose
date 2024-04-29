package com.acdm.restcountriescompose.core.model

import com.acdm.restcountriescompose.core.database.entity.CountriesFromApiEntity

data class Countries(
    val name: NameCountries,
    val capital: List<String>,
    val region: String,
    val subregion: String,
    val borders: List<String>,
    val area: String,
    val flags: FlagsCountries,
    val population: String
)

fun Countries.toDomainChaAttFromApi() = CountriesFromApiEntity(
    name = name.common,
    nameOfficial = name.official,
    capital = capital[0],
    region = region,
    subregion = subregion,
    borders = borders.joinToString(),
    area = area,
    flags = flags.png,
    population = population
)
