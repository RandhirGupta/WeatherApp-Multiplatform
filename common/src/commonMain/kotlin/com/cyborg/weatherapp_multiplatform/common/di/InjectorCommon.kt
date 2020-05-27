package com.cyborg.weatherapp_multiplatform.common.di

import com.cyborg.weatherapp_multiplatform.common.utils.ContextArgs
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object InjectorCommon {

    lateinit var contextArgs: ContextArgs

    fun provideContextArgs(contextArgs: ContextArgs): ContextArgs {
        this.contextArgs = contextArgs
        return this.contextArgs
    }
}