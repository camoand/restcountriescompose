package com.acdm.restcountriescompose.core

import com.acdm.restcountriescompose.core.database.dao.CountriesFromApiDAO
import com.acdm.restcountriescompose.core.database.entity.CountriesFromApiEntity
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val characterFromApiDAO: CountriesFromApiDAO
) {

    fun setDatabaseFromApi(charactersFromApiEntity: List<CountriesFromApiEntity>){
        return characterFromApiDAO.addCountriesFromApi(charactersFromApiEntity)
    }

    fun getDatabaseCountriesFromApi(): List<CountriesFromApiEntity>{
        return characterFromApiDAO.getCountriesDatabase()
    }

}