package com.acdm.restcountriescompose.core

import com.acdm.restcountriescompose.core.model.Countries
import javax.inject.Inject

class ApiRepository @Inject constructor(private val getServiceApi: GetServiceApi){
    suspend fun getApi(): List<Countries> {
        return getServiceApi.getServiceApi()
    }
}