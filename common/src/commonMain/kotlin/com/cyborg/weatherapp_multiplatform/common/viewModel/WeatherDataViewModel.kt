package com.cyborg.weatherapp_multiplatform.common.viewModel

import com.cyborg.weatherapp_multiplatform.common.base.Response
import com.cyborg.weatherapp_multiplatform.common.di.KodeinInjector
import com.cyborg.weatherapp_multiplatform.common.domain.model.CurrentWeatherResponse
import com.cyborg.weatherapp_multiplatform.common.domain.model.WeatherForecastResponse
import com.cyborg.weatherapp_multiplatform.common.domain.usecase.getWeatherData.GetCurrentWeatherRequest
import com.cyborg.weatherapp_multiplatform.common.domain.usecase.getWeatherData.GetCurrentWeatherUseCase
import com.cyborg.weatherapp_multiplatform.common.domain.usecase.getWeatherData.GetWeatherForecastRequest
import com.cyborg.weatherapp_multiplatform.common.domain.usecase.getWeatherData.GetWeatherForecastUseCase
import com.cyborg.weatherapp_multiplatform.common.utils.launchSilent
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.serialization.UnstableDefault
import org.kodein.di.erased.instance
import kotlin.coroutines.CoroutineContext

class WeatherDataViewModel : ViewModel() {

    var getCurrentWeatherLiveData =
        MutableLiveData<WeatherApiState<CurrentWeatherResponse>>(LoadingWeatherApiState())

    var getWeatherForecastLiveData =
        MutableLiveData<WeatherApiState<WeatherForecastResponse>>(LoadingWeatherApiState())

    @UnstableDefault
    private val getCurrentWeatherUseCase by KodeinInjector.instance<GetCurrentWeatherUseCase>()

    @UnstableDefault
    private val getWeatherForecastUseCase by KodeinInjector.instance<GetWeatherForecastUseCase>()

    @UnstableDefault
    private val coroutineContext by KodeinInjector.instance<CoroutineContext>()

    private var job = Job()

    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> }

    @UnstableDefault
    fun getCurrentWeather(url: String) =
        launchSilent(coroutineContext, exceptionHandler, job) {
            getCurrentWeatherLiveData.postValue(LoadingWeatherApiState())

            val request = GetCurrentWeatherRequest(url)
            val response = getCurrentWeatherUseCase.execute(request)
            processCurrentWeatherResponse(response)
        }

    private fun processCurrentWeatherResponse(response: Response<CurrentWeatherResponse>) {

        if (response is Response.Success) {
            getCurrentWeatherLiveData.postValue(SuccessWeatherApiState(response))
        } else if (response is Response.Error) {
            getCurrentWeatherLiveData.postValue(ErrorWeatherApiState(response))
        }
    }

    @UnstableDefault
    fun getWeatherForecast(url: String) = launchSilent(coroutineContext, exceptionHandler, job) {
        getWeatherForecastLiveData.postValue(LoadingWeatherApiState())

        val request = GetWeatherForecastRequest(url)
        val response = getWeatherForecastUseCase.execute(request)
        processWeatherForecastResponse(response)
    }

    private fun processWeatherForecastResponse(forecastResponse: Response<WeatherForecastResponse>) {
        if (forecastResponse is Response.Success) {
            getWeatherForecastLiveData.postValue(SuccessWeatherApiState(forecastResponse))
        } else if (forecastResponse is Response.Error) {
            getWeatherForecastLiveData.postValue(ErrorWeatherApiState(forecastResponse))
        }
    }
}