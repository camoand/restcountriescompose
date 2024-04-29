package com.acdm.restcountriescompose.domain.usecase.database

import com.acdm.restcountriescompose.core.DatabaseRepository
import com.acdm.restcountriescompose.core.database.entity.CountriesFromApiEntity
import javax.inject.Inject

class DbUseCaseInsertFromApi @Inject constructor(private val databaseRepository: DatabaseRepository){

    operator fun invoke(countriesFromApiEntity: List<CountriesFromApiEntity>){
        return databaseRepository.setDatabaseFromApi(countriesFromApiEntity)
    }

}