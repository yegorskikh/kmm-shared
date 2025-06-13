package com.example.kmm_shared
import org.openapitools.client.apis.DefaultApi

class ApiFactory {
    private val factory = provideDefaultApi()

    fun getApi(): DefaultApi {
        return factory.api
    }
}

