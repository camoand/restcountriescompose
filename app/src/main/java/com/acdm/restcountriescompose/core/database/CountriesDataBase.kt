package com.acdm.restcountriescompose.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.acdm.restcountriescompose.core.database.dao.CountriesFromApiDAO
import com.acdm.restcountriescompose.core.database.entity.CountriesFromApiEntity

@Database(entities = [CountriesFromApiEntity::class], version = 1)
abstract class CountriesDataBase : RoomDatabase(){

    abstract fun CountriesFromApiDAO(): CountriesFromApiDAO
}