package com.ambient.stargaze.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.nasa.gov"
private const val API_KEY = "DEMO_KEY"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface ApodApiService {

    @GET("planetary/apod")
    suspend fun getPictureOfTheDay(@Query("api_key")  apiKey : String = API_KEY): ApodProperty

    @GET("planetary/apod")
    suspend fun getPictureOfTheDayByDate(
        @Query("api_key")  apiKey : String = API_KEY,
        @Query("date")  date : String): ApodProperty
}

object ApodApi {
    val retrofitService : ApodApiService by lazy {
        retrofit.create(ApodApiService::class.java)
    }
}