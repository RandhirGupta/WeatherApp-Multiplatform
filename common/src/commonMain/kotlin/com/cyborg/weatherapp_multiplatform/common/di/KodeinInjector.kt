package com.cyborg.weatherapp_multiplatform.common.di

import com.cyborg.weatherapp_multiplatform.common.ApplicationDispatcher
import com.cyborg.weatherapp_multiplatform.common.data.repository.WeatherRepository
import com.cyborg.weatherapp_multiplatform.common.data.source.network.NetworkDataSource
import com.cyborg.weatherapp_multiplatform.common.data.source.network.NetworkDataSourceImpl
import com.cyborg.weatherapp_multiplatform.common.data.source.network.WeatherApi
import com.cyborg.weatherapp_multiplatform.common.domain.usecase.getWeatherData.GetCurrentWeatherUseCase
import kotlinx.serialization.UnstableDefault
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.provider
import org.kodein.di.erased.singleton
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.ThreadLocal

@UnstableDefault
@ThreadLocal
val KodeinInjector = Kodein {

    bind<CoroutineContext>() with provider { ApplicationDispatcher }

    /**
     * UseCases
     */
    bind<GetCurrentWeatherUseCase>() with singleton { GetCurrentWeatherUseCase(instance()) }

    /**
     * Repositories
     */
    bind<WeatherRepository>() with provider { WeatherRepository(instance()) }

    /**
     *Data sources
     */
    bind<NetworkDataSource>() with provider { NetworkDataSourceImpl(instance()) }

    /**
     *Network API
     */
    bind<WeatherApi>() with provider { WeatherApi() }
}