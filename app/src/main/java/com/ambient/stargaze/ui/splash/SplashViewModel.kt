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
import com.ambient.stargaze.network.ApodProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashViewModel() : ViewModel()  {

}