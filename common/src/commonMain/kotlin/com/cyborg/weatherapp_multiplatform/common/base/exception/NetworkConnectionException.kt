package com.cyborg.weatherapp_multiplatform.common.base.exception

class NetworkConnectionException(messageStr: String? = "Internet connection required.") :
    Exception(messageStr)
