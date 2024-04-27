package com.acdm.restcountriescompose.core

import com.acdm.restcountriescompose.core.data.ApiService
import com.acdm.restcountriescompose.core.model.Countries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetServiceApi @Inject constructor(private val apiService: ApiService) {

    suspend fun getServiceApi(): Countries {
        return withContext(Dispatchers.IO){
            val response = apiService.getCountries()
            response
        }
    }

}