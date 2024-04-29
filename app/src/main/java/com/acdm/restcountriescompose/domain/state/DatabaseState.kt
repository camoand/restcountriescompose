package com.acdm.restcountriescompose.domain.state

import com.acdm.restcountriescompose.core.database.entity.CountriesFromApiEntity
import com.acdm.restcountriescompose.core.model.Countries
import com.acdm.restcountriescompose.domain.Constans

data class DatabaseState(
    val isSuccessInDatabase: Boolean = false,
    val isGetInDatabase: Boolean = false,
    val countriesInsertDatabase: Countries? = null,
    val countriesGetDatabase: List<CountriesFromApiEntity>? = null,
    val countriesGetSearchDatabase: List<CountriesFromApiEntity>? = null,
    val isSearch: Boolean = false,
    val error: String = Constans.ERROR_MESSAGE

)
