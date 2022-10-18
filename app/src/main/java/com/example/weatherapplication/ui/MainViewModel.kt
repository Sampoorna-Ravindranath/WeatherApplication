package com.example.weatherapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.data.model.WeatherResponseModel
import com.example.weatherapplication.data.usecase.WeatherUseCase
import com.example.weatherapplication.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val weatherUseCase: WeatherUseCase
) : ViewModel() {

    private val _response: MutableLiveData<Result<WeatherResponseModel>> = MutableLiveData()
    val response: LiveData<Result<WeatherResponseModel>> = _response

    fun fetchWeatherData(city: String) = viewModelScope.launch {

        if (city.isNotEmpty()) {
            weatherUseCase.invoke(city = city).collect { values ->
                _response.value = values
            }
        } else {
            _response.value = Result.Error("Please enter city.", null)
        }
    }
}