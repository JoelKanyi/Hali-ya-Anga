package com.kanyideveloper.haliyaanga.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.kanyideveloper.haliyaanga.data.local.LocationsDao
import com.kanyideveloper.haliyaanga.data.local.LocationsDatabase
import com.kanyideveloper.haliyaanga.data.remote.ApiService
import com.kanyideveloper.haliyaanga.data.repository.DataRepository
import com.kanyideveloper.haliyaanga.util.Constants.BASE_URL
import com.kanyideveloper.haliyaanga.util.Constants.DATABASE_NAME
import com.kanyideveloper.haliyaanga.util.Constants.WEATHER_LOCATION
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPrefs(application: Application): SharedPreferences {
        return application.getSharedPreferences(
            WEATHER_LOCATION,
            MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun provideWeatherLocation(sharedPreferences: SharedPreferences): String {
        return sharedPreferences.getString(WEATHER_LOCATION, "Nairobi") ?: "Nairobi"
    }

    @Provides
    @Singleton
    fun provideLocationsDatabase(application: Application): LocationsDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            LocationsDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideLocationsDao(locationsDatabase: LocationsDatabase) = locationsDatabase.dao

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)

        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDataRepository(
        apiService: ApiService,
        locationsDao: LocationsDao,
        sharedPreferences: SharedPreferences
    ) = DataRepository(apiService, locationsDao, sharedPreferences)
}