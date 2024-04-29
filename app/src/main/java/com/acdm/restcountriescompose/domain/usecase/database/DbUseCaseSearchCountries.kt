package com.acdm.restcountriescompose.domain.usecase.database

import com.acdm.restcountriescompose.core.DatabaseRepository
import com.acdm.restcountriescompose.core.database.entity.CountriesFromApiEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DbUseCaseSearchCountries @Inject constructor(private val databaseRepository: DatabaseRepository){

    suspend operator fun invoke(idCountries: String): List<CountriesFromApiEntity> {
        return withContext(Dispatchers.IO) {
            databaseRepository.getDatabaseSearchCountries(idCountries)
        }
    }

}