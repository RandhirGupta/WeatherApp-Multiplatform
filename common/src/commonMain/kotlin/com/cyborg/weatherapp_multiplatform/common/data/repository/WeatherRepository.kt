package com.cyborg.weatherapp_multiplatform.common.data.repository

import com.cyborg.weatherapp_multiplatform.common.base.Response
import com.cyborg.weatherapp_multiplatform.common.data.source.network.NetworkDataSource
import com.cyborg.weatherapp_multiplatform.common.domain.model.CurrentWeatherResponse
import com.cyborg.weatherapp_multiplatform.common.domain.model.WeatherForecastResponse
import com.cyborg.weatherapp_multiplatform.common.domain.usecase.getWeatherData.GetCurrentWeatherRequest
import com.cyborg.weatherapp_multiplatform.common.domain.usecase.getWeatherData.GetWeatherForecastRequest

class WeatherRepository(private val networkDataSource: NetworkDataSource) {

    suspend fun getCurrentWeatherResponse(requestCurrent: GetCurrentWeatherRequest):
            Response<CurrentWeatherResponse> =
        networkDataSource.getCurrentWeatherResponse(requestCurrent.url)

    suspend fun getForecastWeatherResponse(forecastRequest: GetWeatherForecastRequest): Response<WeatherForecastResponse> =
        networkDataSource.getForecastWeatherResponse(forecastRequest.url)
}