package com.acdm.restcountriescompose.domain.usecase

import com.acdm.restcountriescompose.core.DatabaseRepository
import com.acdm.restcountriescompose.core.database.entity.CountriesFromApiEntity
import javax.inject.Inject

class DbUseCaseGetCountries @Inject constructor(private val databaseRepository: DatabaseRepository) {

    operator fun invoke() : List<CountriesFromApiEntity>{
        return databaseRepository.getDatabaseCountriesFromApi()
    }

}