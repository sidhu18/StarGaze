package com.ambient.stargaze.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.nasa.gov"
private const val API_KEY = "DEMO_KEY"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


interface ApodApiService {

    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key")  apiKey : String = API_KEY):
            Call<String>
}

object ApodApi {
    val retrofitService : ApodApiService by lazy {
        retrofit.create(ApodApiService::class.java)
    }
}