package com.astrodash.data.repository

import com.astrodash.data.api.AstroApi
import com.astrodash.data.api.NewsApi
import com.astrodash.data.model.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AstroRepository {
    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .build()

    private val issApi: AstroApi = Retrofit.Builder().baseUrl("https://api.wheretheiss.at/").client(client).addConverterFactory(GsonConverterFactory.create()).build().create(AstroApi::class.java)
    private val swpcApi: AstroApi = Retrofit.Builder().baseUrl("https://services.swpc.noaa.gov/").client(client).addConverterFactory(GsonConverterFactory.create()).build().create(AstroApi::class.java)
    private val weatherApi: AstroApi = Retrofit.Builder().baseUrl("https://api.open-meteo.com/").client(client).addConverterFactory(GsonConverterFactory.create()).build().create(AstroApi::class.java)
    private val newsApi: NewsApi = Retrofit.Builder().baseUrl("https://api.spaceflightnewsapi.net/").client(client).addConverterFactory(GsonConverterFactory.create()).build().create(NewsApi::class.java)

    suspend fun fetchIss(): IssResponse? = try { issApi.getIssPosition() } catch (e: Exception) { null }
    suspend fun fetchKp(): List<KpItem>? = try { swpcApi.getKpIndex() } catch (e: Exception) { null }
    suspend fun fetchWeather(): WeatherResponse? = try { weatherApi.getWeather() } catch (e: Exception) { null }
    suspend fun fetchAlerts(): List<SwpcAlert>? = try { swpcApi.getAlerts() } catch (e: Exception) { null }
    suspend fun fetchWind(): List<SolarWindItem>? = try { swpcApi.getSolarWind() } catch (e: Exception) { null }
    suspend fun fetchNews(): List<SpaceNewsArticle>? = try { newsApi.getArticles().results } catch (e: Exception) { null }
}
