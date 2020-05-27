package com.cyborg.weatherapp_multiplatform.presentation.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.cyborg.weatherapp_multiplatform.R
import com.cyborg.weatherapp_multiplatform.common.base.Response
import com.cyborg.weatherapp_multiplatform.common.domain.model.CurrentWeatherResponse
import com.cyborg.weatherapp_multiplatform.common.domain.model.WeatherForecastResponse
import com.cyborg.weatherapp_multiplatform.common.utils.convertToDegreeCelsius
import com.cyborg.weatherapp_multiplatform.common.viewModel.*
import com.cyborg.weatherapp_multiplatform.databinding.ActivityLandingBinding
import com.cyborg.weatherapp_multiplatform.presentation.adapter.WeatherForecastAdapter

class LandingActivity : AppCompatActivity() {

    private lateinit var weatherDataViewModel: WeatherDataViewModel
    private lateinit var binding: ActivityLandingBinding
    private lateinit var forecastAdapter: WeatherForecastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_landing)
        binding.lifecycleOwner = this

        initViewModel()
        startRotatingImage()
    }

    @Suppress("EXPERIMENTAL_API_USAGE")
    private fun initViewModel() {
        weatherDataViewModel =
            ViewModelProviders.of(this).get(WeatherDataViewModel::class.java)

        weatherDataViewModel.getCurrentWeather("https://api.openweathermap.org/data/2.5/weather?lat=12.9129744&lon=77.6421572&appid=56233468d738f55639fa78ac26a56e2c")
        weatherDataViewModel.getCurrentWeatherLiveData.addObserver {
            currentWeatherStateMachine(it)
        }

        weatherDataViewModel.getWeatherForecast("https://api.openweathermap.org/data/2.5/forecast/daily?q=bengaluru&appid=56233468d738f55639fa78ac26a56e2c")
        weatherDataViewModel.getWeatherForecastLiveData.addObserver {
            weatherForecastStateMachine(it)
        }
    }

    private fun currentWeatherStateMachine(state: WeatherApiState<CurrentWeatherResponse>) {

        when (state) {

            is SuccessWeatherApiState -> {
                val response = state.response as Response.Success
                Log.d("Current weather", response.data.name)
            }

            is LoadingWeatherApiState -> {
                Log.d("Current weather", "Loading current weather")
            }

            is ErrorWeatherApiState -> {
                val response = state.response as Response.Error
                Log.d("Current weather", response.exception.toString())
            }
        }
    }

    private fun weatherForecastStateMachine(state: WeatherApiState<WeatherForecastResponse>) {

        when (state) {

            is SuccessWeatherApiState -> {
                val response = state.response as Response.Success
                handleSuccessResult(response.data)
                Log.d("Forecast weather", response.data.city.name)
            }

            is LoadingWeatherApiState -> {
                binding.loadingView.visibility = View.VISIBLE
                Log.d("Forecast weather", "Loading current weather")
            }

            is ErrorWeatherApiState -> {
                binding.loadingView.visibility = View.GONE
                binding.errorLayout.visibility = View.GONE
                val response = state.response as Response.Error
                Log.d("Forecast weather", response.exception.toString())
            }
        }
    }

    private fun handleSuccessResult(weatherForecastResponse: WeatherForecastResponse) {

        binding.forecastLocationTextView.text = weatherForecastResponse.city.name
        binding.forecastTempTextView.text =
            String.format(
                "%s %s",
                String.format(
                    "%.2f",
                    convertToDegreeCelsius(weatherForecastResponse.list[0].temp.max)
                ),
                "\u2103"
            )

        binding.loadingView.visibility = View.GONE
        binding.successLayout.visibility = View.VISIBLE
        binding.errorLayout.visibility = View.GONE

        val forecastDayList = weatherForecastResponse.list.toMutableList()
        forecastDayList.removeAt(0)
        forecastAdapter = WeatherForecastAdapter(forecastDayList)
        binding.forecastRecyclerView.adapter = forecastAdapter
    }

    private fun startRotatingImage() {
        val startRotateAnimation = AnimationUtils.loadAnimation(this, R.anim.linear_interpolator)
        binding.loadingView.startAnimation(startRotateAnimation)
    }
}
