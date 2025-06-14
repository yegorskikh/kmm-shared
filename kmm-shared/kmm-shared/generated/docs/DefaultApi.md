# DefaultApi

All URIs are relative to *https://rickandmortyapi.com/api*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**episodeGet**](DefaultApi.md#episodeGet) | **GET** /episode | Get all episodes |


<a id="episodeGet"></a>
# **episodeGet**
> EpisodesResponse episodeGet()

Get all episodes

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
try {
    val result : EpisodesResponse = apiInstance.episodeGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#episodeGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#episodeGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**EpisodesResponse**](EpisodesResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

