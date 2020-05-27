package com.cyborg.weatherapp_multiplatform.common.data.source.network

import com.cyborg.weatherapp_multiplatform.common.base.Response
import com.cyborg.weatherapp_multiplatform.common.domain.model.CurrentWeatherResponse
import com.cyborg.weatherapp_multiplatform.common.domain.model.WeatherForecastResponse
import kotlinx.serialization.UnstableDefault

@UnstableDefault
class NetworkDataSourceImpl(private val weatherApi: WeatherApi) :
    NetworkDataSource() {

    override suspend fun getCurrentWeatherResponse(url: String): Response<CurrentWeatherResponse> =
        weatherApi.getCurrentWeatherResponse(url)

    override suspend fun getForecastWeatherResponse(url: String): Response<WeatherForecastResponse> =
        weatherApi.getForecastWeatherResponse(url)
}