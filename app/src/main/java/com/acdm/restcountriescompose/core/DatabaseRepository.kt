package com.acdm.restcountriescompose.core

import com.acdm.restcountriescompose.core.database.dao.CountriesFromApiDAO
import com.acdm.restcountriescompose.core.database.entity.CountriesFromApiEntity
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val countriesFromApiDAO: CountriesFromApiDAO
) {

    fun setDatabaseFromApi(countriesFromApiEntity: List<CountriesFromApiEntity>){
        return countriesFromApiDAO.addCountriesFromApi(countriesFromApiEntity)
    }

    fun getDatabaseCountriesFromApi(): List<CountriesFromApiEntity>{
        return countriesFromApiDAO.getCountriesDatabase()
    }

    fun getDatabaseSearchCountries(idCountries: String): List<CountriesFromApiEntity>{
        return countriesFromApiDAO.getSearchCountries(idCountries)
    }

}