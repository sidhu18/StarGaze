package com.ambient.stargaze.data.dao

import androidx.room.*
import com.ambient.stargaze.data.entities.NasaApodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NasaApodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(nasaApodEntity : NasaApodEntity)

    @Update
    suspend fun update(nasaApodEntity: NasaApodEntity)

    @Query("SELECT * FROM nasa_apod_table ORDER BY id DESC LIMIT 1")
    suspend fun getLatestApod() : NasaApodEntity?

    @Query("SELECT * FROM nasa_apod_table WHERE date = :date")
    suspend fun getApodByDate(date : String) : NasaApodEntity?

}