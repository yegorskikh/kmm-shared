package com.example.kmm_shared

import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import org.openapitools.client.apis.DefaultApi
import org.openapitools.client.infrastructure.ApiClient
import io.ktor.client.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json
import io.ktor.serialization.kotlinx.json.*

class IOSApiFactory : FactoryProtocol {
    override val api = DefaultApi(
        baseUrl = ApiClient.BASE_URL,
        httpClient = HttpClient(Darwin) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
        }
    )
}

actual fun provideDefaultApi(): FactoryProtocol = IOSApiFactory()