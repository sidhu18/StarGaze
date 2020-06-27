package com.ambient.stargaze.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nasa_apod_table")
class NasaApodEntity {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    @ColumnInfo(name = "copyright")
    var copyright: String? = null

    @ColumnInfo(name = "date")
    var date: String? = null

    @ColumnInfo(name = "explanation")
    var explanation: String? = null

    @ColumnInfo(name = "hdurl")
    var hdurl: String? = null

    @ColumnInfo(name = "media_type")
    var mediaType: String? = null

    @ColumnInfo(name = "service_version")
    var serviceVersion: String? = null

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "url")
    var url: String? = null
}