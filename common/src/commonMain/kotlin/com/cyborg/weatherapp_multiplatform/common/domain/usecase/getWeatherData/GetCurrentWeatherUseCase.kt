package com.cyborg.weatherapp_multiplatform.common.domain.usecase.getWeatherData

import com.cyborg.weatherapp_multiplatform.common.base.Response
import com.cyborg.weatherapp_multiplatform.common.data.repository.WeatherRepository
import com.cyborg.weatherapp_multiplatform.common.domain.model.CurrentWeatherResponse
import com.cyborg.weatherapp_multiplatform.common.domain.usecase.base.BaseUseCase

class GetCurrentWeatherUseCase(
    private val repository: WeatherRepository
) : BaseUseCase<GetCurrentWeatherRequest, CurrentWeatherResponse>() {

    override suspend fun run(): Response<CurrentWeatherResponse> =
        repository.getCurrentWeatherResponse(request!!)
}