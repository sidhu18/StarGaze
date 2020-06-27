package com.ambient.stargaze.data.repositories

import com.ambient.stargaze.data.dao.NasaApodDao
import com.ambient.stargaze.data.entities.NasaApodEntity

data class NasaApodRepository(private var nasaApodDao: NasaApodDao){

    fun insertApod(nasaApodEntity: NasaApodEntity){
        nasaApodDao.insert(nasaApodEntity)
    }

    fun updateApod(nasaApodEntity: NasaApodEntity){
        nasaApodDao.update(nasaApodEntity)
    }

    fun getLatestApod() : NasaApodEntity? {
        return nasaApodDao.getLatestApod()
    }

    fun getApodByDate(date : String) : NasaApodEntity? {
        return nasaApodDao.getApodByDate(date)
    }

}