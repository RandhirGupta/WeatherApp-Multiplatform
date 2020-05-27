package com.cyborg.weatherapp_multiplatform.common.domain.usecase.getWeatherData

import com.cyborg.weatherapp_multiplatform.common.base.Response
import com.cyborg.weatherapp_multiplatform.common.data.repository.WeatherRepository
import com.cyborg.weatherapp_multiplatform.common.domain.model.WeatherForecastResponse
import com.cyborg.weatherapp_multiplatform.common.domain.usecase.base.BaseUseCase

class GetWeatherForecastUseCase(private val repository: WeatherRepository) :
    BaseUseCase<GetWeatherForecastRequest, WeatherForecastResponse>() {

    override suspend fun run(): Response<WeatherForecastResponse> =
        repository.getForecastWeatherResponse(request!!)
}