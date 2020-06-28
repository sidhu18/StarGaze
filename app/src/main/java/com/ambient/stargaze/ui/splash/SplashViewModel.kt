package com.ambient.stargaze.ui.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ambient.stargaze.data.StarGazeDatabase
import com.ambient.stargaze.data.entities.NasaApodEntity
import com.ambient.stargaze.data.repositories.NasaApodRepository
import com.ambient.stargaze.network.ApodApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashViewModel(application: Application) : AndroidViewModel(application)  {

    private val apodRepository : NasaApodRepository
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _apodResponse = MutableLiveData<String>()
    val apodResponse : LiveData<String> = _apodResponse

    init {
        val apodDao = StarGazeDatabase.getInstance(application).nasaApodDao
        apodRepository = NasaApodRepository(apodDao)
        getPictureOfTheDay()
    }

    private fun getPictureOfTheDay(){
        ApodApi.retrofitService.getPictureOfTheDay().enqueue(
            object : Callback<String>{
                override fun onFailure(call: Call<String>, t: Throwable) {
                    _apodResponse.value = "Failure: " + t.message
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    _apodResponse.value = response.body()
                }

            }
        )
    }
}