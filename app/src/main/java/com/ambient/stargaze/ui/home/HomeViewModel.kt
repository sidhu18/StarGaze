package com.ambient.stargaze.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.ambient.stargaze.data.StarGazeDatabase
import com.ambient.stargaze.data.entities.NasaApodEntity
import com.ambient.stargaze.data.repositories.NasaApodRepository
import com.ambient.stargaze.helpers.StringUtils
import com.ambient.stargaze.network.ApodApi
import com.ambient.stargaze.network.ApodProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val apodRepository : NasaApodRepository
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _apodResponse = MutableLiveData<NasaApodEntity>()
    val apodResponse : LiveData<NasaApodEntity> = _apodResponse

    init {
        apodRepository = NasaApodRepository(StarGazeDatabase.getInstance(application))
        fetchApodByDate(StringUtils.formatDateToString(Date(System.currentTimeMillis())))
    }

    private fun getLatestApod(){
        uiScope.launch {
            val response = apodRepository.getLatestApod()
            _apodResponse.value = response
        }
    }

    fun getPictureOfTheDay(date : String){
        uiScope.launch {
            val response = apodRepository.getApodByDate(date)
            _apodResponse.value = response
        }
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                apodRepository.refreshVideos()
            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
            }
        }

    }

    fun fetchApodByDate(date: String) {
        viewModelScope.launch {
            try {
                val response = apodRepository.fetchApodByDate(date)
                _apodResponse.value = response
            } catch (networkError: IOException) {
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}