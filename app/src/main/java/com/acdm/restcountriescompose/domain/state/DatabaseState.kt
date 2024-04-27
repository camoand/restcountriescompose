package com.acdm.restcountriescompose.domain.state

import com.acdm.restcountriescompose.core.database.entity.CountriesFromApiEntity
import com.acdm.restcountriescompose.core.model.Countries
import com.acdm.restcountriescompose.domain.Constans

data class DatabaseState(
    val isSuccessInDatabase: Boolean = false,
    val isCreateDatabase: Boolean = false,
    val isFavorite: Boolean = false,
    val isInDatabase: Int = 0,
    val isGetInDatabase: Boolean = false,
    val isGetInDatabaseFavorite: Boolean = false,
    val characterInsertDatabase: Countries? = null,
    val countriesGetDatabase: List<CountriesFromApiEntity>? = null,
    //val characterGetDatabaseFavorite: List<CharactersAttributesEntity>? = null,
    val error: String = Constans.ERROR_MESSAGE

)
