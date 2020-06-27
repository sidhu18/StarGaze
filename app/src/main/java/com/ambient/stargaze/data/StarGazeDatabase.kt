package com.ambient.stargaze.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ambient.stargaze.data.dao.NasaApodDao
import com.ambient.stargaze.data.entities.NasaApodEntity

@Database(entities = [NasaApodEntity::class],version = 1,exportSchema = false)
abstract class StarGazeDatabase : RoomDatabase(){

    abstract val nasaApodDao : NasaApodDao

    companion object {

        @Volatile
        private var INSTANCE: StarGazeDatabase? = null

        fun getInstance(context: Context): StarGazeDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StarGazeDatabase::class.java,
                        "stargaze_app_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    }