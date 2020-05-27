package com.cyborg.weatherapp_multiplatform.common.domain.usecase.getWeatherData

import com.cyborg.weatherapp_multiplatform.common.domain.usecase.base.BaseRequest

class GetWeatherForecastRequest(val url: String) : BaseRequest {

    override fun validate(): Boolean = true
}