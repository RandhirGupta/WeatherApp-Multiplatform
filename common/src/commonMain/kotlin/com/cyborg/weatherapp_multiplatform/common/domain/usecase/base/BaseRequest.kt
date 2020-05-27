package com.cyborg.weatherapp_multiplatform.common.domain.usecase.base

interface BaseRequest {
    fun validate(): Boolean
}