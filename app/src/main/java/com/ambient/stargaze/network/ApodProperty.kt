package com.ambient.stargaze.network

import androidx.lifecycle.Transformations.map
import com.ambient.stargaze.data.entities.NasaApodEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class ApodProperty(
    @Json(name = "date") var date: String,
    @Json(name = "explanation") var explanation: String,
    @Json(name = "hdurl") var hdurl: String?,
    @Json(name = "media_type") var mediaType: String,
    @Json(name = "service_version") var serviceVersion: String,
    @Json(name = "title") var title: String,
    @Json(name = "url") var url: String
)

fun ApodProperty.asDomainModel(): NasaApodEntity {
    return NasaApodEntity(
        date =  date,
        explanation = explanation,
        hdurl = hdurl,
        mediaType = mediaType,
        serviceVersion = serviceVersion,
        title = title,
        url = url
    )
}
