package com.example.kmm_shared

import org.openapitools.client.apis.DefaultApi

interface FactoryProtocol {
    val api: DefaultApi
}

expect fun provideDefaultApi(): FactoryProtocol