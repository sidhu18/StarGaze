package com.ambient.stargaze.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ambient.stargaze.data.entities.NasaApodEntity

@Dao
interface NasaApodDao {

    @Insert
    fun insert(nasaApodEntity : NasaApodEntity)

    @Update
    fun update(nasaApodEntity: NasaApodEntity)

    @Query("SELECT * FROM nasa_apod_table ORDER BY id DESC LIMIT 1")
    fun getLatestApod() : NasaApodEntity?

    @Query("SELECT * FROM nasa_apod_table WHERE date = :date")
    fun getApodByDate(date : String) : NasaApodEntity?

}