package com.astrodash.data.api

import com.astrodash.data.model.*
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v1/satellites/25544")
    suspend fun getIssPosition(): IssResponse

    @GET("products/noaa-planetary-k-index.json")
    suspend fun getKpIndex(): List<KpItem>

    @GET("v1/forecast?latitude=1.3521&longitude=103.8198&current_weather=true")
    suspend fun getWeather(): WeatherResponse

    @GET("products/alerts.json")
    suspend fun getAlerts(): List<SwpcAlert>

    @GET("json/rtsw/rtsw_wind_1m.json")
    suspend fun getSolarWind(): List<SolarWindItem>

    @GET("v4/articles/?limit=5")
    suspend fun getSpaceNews(): SpaceNewsWrapper
}

data class SpaceNewsWrapper(
    val results: List<SpaceNewsArticle>
)
