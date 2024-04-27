package com.acdm.restcountriescompose.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.acdm.restcountriescompose.core.database.entity.CountriesFromApiEntity

@Dao
interface CountriesFromApiDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCountriesFromApi(countriesFromApiEntity: List<CountriesFromApiEntity>)


    @Query("SELECT * FROM CountriesFromApiEntity")
    fun  getCountriesDatabase() : MutableList<CountriesFromApiEntity>

}