package com.cyborg.weatherapp_multiplatform.common.viewModel

import com.cyborg.weatherapp_multiplatform.common.base.Response

sealed class WeatherApiState<T> {
    abstract val response: Response<T>?
}

data class SuccessWeatherApiState<T>(override val response: Response<T>) :
    WeatherApiState<T>()

data class LoadingWeatherApiState<T>(override val response: Response<T>? = null) :
    WeatherApiState<T>()

data class ErrorWeatherApiState<T>(override val response: Response<T>) :
    WeatherApiState<T>()
