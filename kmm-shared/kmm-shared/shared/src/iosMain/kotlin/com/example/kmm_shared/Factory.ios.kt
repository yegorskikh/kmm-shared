package com.example.kmm_shared

import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import org.openapitools.client.apis.DefaultApi
import org.openapitools.client.infrastructure.ApiClient

class IOSApiFactory : FactoryProtocol {
    override val api = DefaultApi(
        baseUrl = ApiClient.BASE_URL,
        httpClient = HttpClient(Darwin)
    )
}

actual fun provideDefaultApi(): FactoryProtocol = IOSApiFactory()