package com.cyborg.weatherapp_multiplatform.common.data.source.network

import com.cyborg.weatherapp_multiplatform.common.base.Response
import com.cyborg.weatherapp_multiplatform.common.base.exception.NetworkConnectionException
import com.cyborg.weatherapp_multiplatform.common.domain.model.CurrentWeatherResponse
import com.cyborg.weatherapp_multiplatform.common.domain.model.WeatherForecastResponse
import com.cyborg.weatherapp_multiplatform.common.utils.isNetworkAvailable
import io.ktor.client.HttpClient
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json

class WeatherApi {

    private val httpClient = HttpClient {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }

    @UnstableDefault
    suspend fun getCurrentWeatherResponse(url: String): Response<CurrentWeatherResponse> {
        return try {
            if (isNetworkAvailable()) {
                val json = httpClient.get<HttpResponse>(url).readText()
                val response = Json.nonstrict.parse(CurrentWeatherResponse.serializer(), json)
                Response.Success(response)
            } else {
                Response.Error(NetworkConnectionException())
            }
        } catch (exception: Exception) {
            Response.Error(exception)
        }
    }

    @UnstableDefault
    suspend fun getForecastWeatherResponse(url: String): Response<WeatherForecastResponse> {
        return try {
            if (isNetworkAvailable()) {
                val json = httpClient.get<HttpResponse>(url).readText()
                val response = Json.nonstrict.parse(WeatherForecastResponse.serializer(), json)
                Response.Success(response)
            } else {
                Response.Error(NetworkConnectionException())
            }
        } catch (exception: Exception) {
            Response.Error(exception)
        }
    }
}