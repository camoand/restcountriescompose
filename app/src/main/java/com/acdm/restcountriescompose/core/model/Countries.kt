package com.acdm.restcountriescompose.core.model

data class Countries(
    val name: NameCountries,
    val currencies: CurrenciesCountries,
    val capital: String,
    val region: String,
    val subregion: String,
    val languages: LanguagesCountries,
    val flags: FlagsCountries,


)
