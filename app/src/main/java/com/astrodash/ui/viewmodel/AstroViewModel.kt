package com.astrodash.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.astrodash.data.model.*
import com.astrodash.data.repository.AstroRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AstroViewModel : ViewModel() {
    private val repository = AstroRepository()

    private val _issState = MutableStateFlow<IssResponse?>(null)
    val issState: StateFlow<IssResponse?> = _issState.asStateFlow()

    private val _kpState = MutableStateFlow<List<KpItem>?>(null)
    val kpState: StateFlow<List<KpItem>?> = _kpState.asStateFlow()

    private val _weatherState = MutableStateFlow<WeatherResponse?>(null)
    val weatherState: StateFlow<WeatherResponse?> = _weatherState.asStateFlow()

    private val _alertsState = MutableStateFlow<List<SwpcAlert>?>(null)
    val alertsState: StateFlow<List<SwpcAlert>?> = _alertsState.asStateFlow()

    private val _windState = MutableStateFlow<List<SolarWindItem>?>(null)
    val windState: StateFlow<List<SolarWindItem>?> = _windState.asStateFlow()

    private val _newsState = MutableStateFlow<List<SpaceNewsArticle>?>(null)
    val newsState: StateFlow<List<SpaceNewsArticle>?> = _newsState.asStateFlow()

    init {
        fetchAll()
    }

    fun fetchAll() {
        viewModelScope.launch {
            launch { _issState.value = repository.fetchIss() }
            launch { _kpState.value = repository.fetchKp() }
            launch { _weatherState.value = repository.fetchWeather() }
            launch { _alertsState.value = repository.fetchAlerts() }
            launch { _windState.value = repository.fetchWind() }
            launch { _newsState.value = repository.fetchNews() }
        }
    }
}
