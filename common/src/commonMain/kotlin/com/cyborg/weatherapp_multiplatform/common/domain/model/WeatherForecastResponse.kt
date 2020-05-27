package com.cyborg.weatherapp_multiplatform.common.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecastResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ForecastList>,
    val message: Double
)

@Serializable
data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val timezone: Int
)

@Serializable
data class FeelsLike(
    val day: Double,
    val eve: Double,
    val morn: Double,
    val night: Double
)

@Serializable
data class Temp(
    val day: Double,
    val eve: Double,
    val max: Double,
    val min: Double,
    val morn: Double,
    val night: Double
)

@Serializable
data class ForecastList(
    val clouds: Int,
    val deg: Int,
    val dt: Long,
    val feels_like: FeelsLike,
    val humidity: Int,
    val pressure: Int,
    val rain: Double,
    val speed: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    val weather: List<Weather>
)