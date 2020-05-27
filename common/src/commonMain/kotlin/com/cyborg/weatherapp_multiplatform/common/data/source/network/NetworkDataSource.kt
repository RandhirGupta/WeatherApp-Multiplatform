package com.cyborg.weatherapp_multiplatform.common.data.source.network

import com.cyborg.weatherapp_multiplatform.common.base.Response
import com.cyborg.weatherapp_multiplatform.common.domain.model.CurrentWeatherResponse
import com.cyborg.weatherapp_multiplatform.common.domain.model.WeatherForecastResponse

abstract class NetworkDataSource {
    abstract suspend fun getCurrentWeatherResponse(url: String): Response<CurrentWeatherResponse>

    abstract suspend fun getForecastWeatherResponse(url: String): Response<WeatherForecastResponse>
}