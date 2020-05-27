package com.cyborg.weatherapp_multiplatform.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.cyborg.weatherapp_multiplatform.R
import com.cyborg.weatherapp_multiplatform.common.domain.model.ForecastList
import com.cyborg.weatherapp_multiplatform.common.utils.getDayName
import com.cyborg.weatherapp_multiplatform.databinding.WeatherForecastItemLayoutBinding

class WeatherForecastAdapter(private var forecastList: List<ForecastList>?) :
    RecyclerView.Adapter<WeatherForecastAdapter.WeatherForecastViewHolder>() {

    private var mContext: Context? = null
    private var lastPosition = -1

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): WeatherForecastViewHolder {
        this.mContext = p0.context
        val layoutInflater = LayoutInflater.from(p0.context)
        val dataBinding = WeatherForecastItemLayoutBinding.inflate(layoutInflater, p0, false)
        return WeatherForecastViewHolder(dataBinding)
    }

    override fun onBindViewHolder(p0: WeatherForecastViewHolder, p1: Int) {
        val forecast = forecastList?.get(p1)
        p0.binding.tvForecastDay.text = forecast?.dt?.let { getDayName(it) }
        p0.binding.tvForecastTemp.text =
            String.format("%s %s", forecast?.temp?.max.toString(), "\u2103")

        setAnimation(p0.itemView, p1)
    }

    override fun getItemCount(): Int {
        return forecastList?.size!!
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_bottom)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    class WeatherForecastViewHolder(var binding: WeatherForecastItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}