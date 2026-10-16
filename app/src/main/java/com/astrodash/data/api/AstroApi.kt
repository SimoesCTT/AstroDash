package com.astrodash.data.api

import com.astrodash.data.model.*
import retrofit2.http.GET
import retrofit2.http.Query

interface AstroApi {
    @GET("v1/satellites/25544")
    suspend fun getIssPosition(): IssResponse

    @GET("products/noaa-planetary-k-index.json")
    suspend fun getKpIndex(): List<KpItem>

    @GET("v1/forecast")
    suspend fun getWeather(
        @Query("latitude") lat: Double = 1.3067,
        @Query("longitude") lon: Double = 103.7610,
        @Query("current_weather") current: Boolean = true
    ): WeatherResponse

    @GET("products/alerts.json")
    suspend fun getAlerts(): List<SwpcAlert>

    @GET("json/rtsw/rtsw_wind_1m.json")
    suspend fun getSolarWind(): List<SolarWindItem>
}

interface NewsApi {
    @GET("v4/articles/")
    suspend fun getArticles(@Query("limit") limit: Int = 5): NewsResponse
}

data class NewsResponse(
    val results: List<SpaceNewsArticle>
)
