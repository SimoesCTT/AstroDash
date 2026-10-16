package com.astrodash.data.model

import com.google.gson.annotations.SerializedName

data class IssResponse(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val altitude: Double,
    val velocity: Double,
    val visibility: String
)

data class KpItem(
    @SerializedName("time_tag") val timeTag: String,
    @SerializedName("Kp") val kp: Double
)

data class WeatherResponse(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    @SerializedName("current_weather") val currentWeather: CurrentWeather?
)

data class CurrentWeather(
    val temperature: Double,
    @SerializedName("windspeed") val windSpeed: Double,
    @SerializedName("winddirection") val windDirection: Double,
    val weathercode: Int,
    @SerializedName("is_day") val isDay: Int,
    val time: String
)

data class SwpcAlert(
    val message_issue_date: String?,
    val product_id: String?,
    val message: String?
)

data class SolarWindItem(
    @SerializedName("time_tag") val timeTag: String?,
    @SerializedName("proton_density") val density: Double?,
    @SerializedName("proton_speed") val speed: Double?,
    @SerializedName("temperature") val temperature: Double?,
    val active: Boolean?
)

data class SpaceNewsArticle(
    val id: Int,
    val title: String,
    val news_site: String,
    val url: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("published_at") val publishedAt: String
)
