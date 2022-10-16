package com.example.weatherapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.data.model.WeatherResponseModel
import com.example.weatherapplication.data.repository.MainRepository
import com.example.weatherapplication.utils.NetworkHelper
import com.example.weatherapplication.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _weatherData = MutableLiveData<Resource<WeatherResponseModel>>()
    val weatherData: LiveData<Resource<WeatherResponseModel>> get() = _weatherData

    fun fetchWeatherData(city: String) {
        viewModelScope.launch {
            _weatherData.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getWeatherData(city = city).let {
                    if (it.isSuccessful) {
                        _weatherData.postValue(Resource.success(it.body()))
                    } else _weatherData.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _weatherData.postValue(Resource.error("No internet connection", null))
        }
    }
}