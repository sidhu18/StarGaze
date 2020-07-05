package com.ambient.stargaze.data.repositories

import com.ambient.stargaze.data.StarGazeDatabase
import com.ambient.stargaze.data.dao.NasaApodDao
import com.ambient.stargaze.data.entities.NasaApodEntity
import com.ambient.stargaze.network.ApodApi
import com.ambient.stargaze.network.ApodApiService
import com.ambient.stargaze.network.ApodProperty
import com.ambient.stargaze.network.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class NasaApodRepository(private val database: StarGazeDatabase){

//    val apod: NasaApodEntity? = database.nasaApodDao.getLatestApod()

    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            val apod = getPictureOfTheDay()
            apod?.asDomainModel()?.let { insertApod(it) }
        }
    }

    suspend fun fetchApodByDate(date: String) : NasaApodEntity? {
        withContext(Dispatchers.IO) {
            if(getApodByDate(date) == null) {
                val apod = getPictureOfTheDayByDate(date)
                apod?.asDomainModel()?.let {
                    insertApod(it)
                }
            }
        }
        return getApodByDate(date)
    }


    var client: ApodApiService = ApodApi.retrofitService

    suspend fun insertApod(nasaApodEntity: NasaApodEntity){
        database.nasaApodDao.insert(nasaApodEntity)
    }

    suspend fun updateApod(nasaApodEntity: NasaApodEntity){
        database.nasaApodDao.update(nasaApodEntity)
    }

    private suspend fun getPictureOfTheDay() : ApodProperty? {
        return client.getPictureOfTheDay()
    }

    private suspend fun getPictureOfTheDayByDate(date: String) : ApodProperty? {
        return client.getPictureOfTheDayByDate(date = date)
    }

    suspend fun getLatestApod() : NasaApodEntity?{
        return database.nasaApodDao.getLatestApod()
    }

    suspend fun getApodByDate(date : String) : NasaApodEntity? {
        return database.nasaApodDao.getApodByDate(date)
    }

}