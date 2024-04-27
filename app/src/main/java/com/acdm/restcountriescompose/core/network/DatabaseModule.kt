package com.acdm.restcountriescompose.core.network

import android.content.Context
import androidx.room.Room
import com.acdm.restcountriescompose.core.database.CountriesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val COUNTRIES_DATABASE_NAME = "CountriesDataBase"
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(context, CountriesDataBase::class.java, COUNTRIES_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideCountriesFromApiDao(database: CountriesDataBase) = database.CountriesFromApiDAO()


}