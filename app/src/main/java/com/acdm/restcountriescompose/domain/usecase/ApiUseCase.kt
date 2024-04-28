package com.acdm.restcountriescompose.domain.usecase

import com.acdm.restcountriescompose.core.ApiRepository
import com.acdm.restcountriescompose.core.model.Countries
import javax.inject.Inject

class ApiUseCase @Inject constructor(private val repository: ApiRepository) {

    suspend operator fun invoke(): List<Countries> {
        return repository.getApi()
    }

}