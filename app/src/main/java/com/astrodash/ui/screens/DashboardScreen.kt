package com.astrodash.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.astrodash.R
import com.astrodash.ui.viewmodel.AstroViewModel

@Composable
fun WidgetCard(content: @Composable ColumnScope.() -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E222B)),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(24.dp), content = content)
    }
}

fun getWeatherDesc(code: Int): String {
    return when (code) {
        0 -> "Clear Sky"
        1, 2, 3 -> "Partly Cloudy"
        45, 48 -> "Foggy"
        51, 53, 55 -> "Light Drizzle"
        61, 63, 65 -> "Rain Showers"
        71, 73, 75 -> "Snow Fall"
        95, 96, 99 -> "Thunderstorm"
        else -> "Fair Conditions"
    }
}

@Composable
fun DashboardScreen(viewModel: AstroViewModel = AstroViewModel()) {
    val issData by viewModel.issState.collectAsState()
    val kpData by viewModel.kpState.collectAsState()
    val weatherData by viewModel.weatherState.collectAsState()
    val alertsData by viewModel.alertsState.collectAsState()
    val windData by viewModel.windState.collectAsState()
    val newsData by viewModel.newsState.collectAsState()
    val uriHandler = LocalUriHandler.current

    val WidgetColorScheme = darkColorScheme(
        primary = Color(0xFF60A5FA),
        background = Color(0xFF111318),
        surface = Color(0xFF1E222B),
        onBackground = Color(0xFFF3F4F6),
        onSurface = Color(0xFFF3F4F6)
    )

    MaterialTheme(colorScheme = WidgetColorScheme) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "SINGAPORE",
                                style = MaterialTheme.typography.labelMedium.copy(letterSpacing = 1.5.sp),
                                color = Color(0xFF9CA3AF)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "AstroDash",
                                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFFF3F4F6)
                            )
                        }
                        Surface(
                            color = Color(0xFF2D3748),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "ONLINE",
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                color = Color(0xFF34D399),
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                            )
                        }
                    }
                }

                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E222B)),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                try {
                                    uriHandler.openUri("https://www.nasa.gov/nasatv/")
                                } catch (e: Exception) {}
                            }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text("NASA TELEVISION", style = MaterialTheme.typography.labelSmall, color = Color(0xFF9CA3AF), fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(2.dp))
                                Text("Launch Official Stream Feed", style = MaterialTheme.typography.bodyLarge, color = Color(0xFF60A5FA), fontWeight = FontWeight.SemiBold)
                            }
                            Surface(color = Color(0xFF065F46), shape = RoundedCornerShape(6.dp)) {
                                Text("FEED", style = MaterialTheme.typography.labelSmall, color = Color(0xFF6EE7B7), modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                            }
                        }
                    }
                }

                item {
                    WidgetCard {
                        Text("LOCAL WEATHER (SINGAPORE)", style = MaterialTheme.typography.labelMedium, color = Color(0xFF9CA3AF), fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(16.dp))
                        val cw = weatherData?.currentWeather
                        if (cw != null) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = "${cw.temperature.toInt()}°C",
                                        style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Light, fontSize = 64.sp),
                                        color = Color(0xFFF3F4F6)
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = getWeatherDesc(cw.weathercode),
                                        style = MaterialTheme.typography.titleMedium,
                                        color = Color(0xFF60A5FA),
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                                Column(horizontalAlignment = Alignment.End) {
                                    Text("Wind Speed", style = MaterialTheme.typography.labelMedium, color = Color(0xFF9CA3AF))
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text("${cw.windSpeed} km/h", style = MaterialTheme.typography.bodyLarge, color = Color(0xFFF3F4F6), fontWeight = FontWeight.SemiBold)
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Text("Direction", style = MaterialTheme.typography.labelMedium, color = Color(0xFF9CA3AF))
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text("${cw.windDirection}°", style = MaterialTheme.typography.bodyLarge, color = Color(0xFFF3F4F6), fontWeight = FontWeight.SemiBold)
                                }
                            }
                        } else {
                            Text("Loading weather data...", style = MaterialTheme.typography.bodyLarge, color = Color(0xFF9CA3AF), modifier = Modifier.padding(vertical = 16.dp))
                        }
                    }
                }

                item {
                    WidgetCard {
                        Text("ISS ORBITAL TELEMETRY", style = MaterialTheme.typography.labelMedium, color = Color(0xFF9CA3AF), fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(16.dp))
                        val iss = issData
                        if (iss != null) {
                            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text("Altitude", style = MaterialTheme.typography.bodyMedium, color = Color(0xFF9CA3AF))
                                    Text("${iss.altitude} km", style = MaterialTheme.typography.bodyLarge, color = Color(0xFFF3F4F6), fontWeight = FontWeight.Bold)
                                }
                                HorizontalDivider(color = Color(0xFF2D3748), thickness = 0.5.dp)
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text("Velocity", style = MaterialTheme.typography.bodyMedium, color = Color(0xFF9CA3AF))
                                    Text("${iss.velocity} km/h", style = MaterialTheme.typography.bodyLarge, color = Color(0xFF60A5FA), fontWeight = FontWeight.Bold)
                                }
                                HorizontalDivider(color = Color(0xFF2D3748), thickness = 0.5.dp)
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text("Latitude", style = MaterialTheme.typography.bodyMedium, color = Color(0xFF9CA3AF))
                                    Text("${iss.latitude}°", style = MaterialTheme.typography.bodyLarge, color = Color(0xFFF3F4F6), fontWeight = FontWeight.Bold)
                                }
                                HorizontalDivider(color = Color(0xFF2D3748), thickness = 0.5.dp)
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text("Longitude", style = MaterialTheme.typography.bodyMedium, color = Color(0xFF9CA3AF))
                                    Text("${iss.longitude}°", style = MaterialTheme.typography.bodyLarge, color = Color(0xFFF3F4F6), fontWeight = FontWeight.Bold)
                                }
                            }
                        } else {
                            Text("Acquiring ISS orbital fix...", style = MaterialTheme.typography.bodyLarge, color = Color(0xFF9CA3AF))
                        }
                    }
                }

                item {
                    WidgetCard {
                        Text("GEOMAGNETIC KP INDEX", style = MaterialTheme.typography.labelMedium, color = Color(0xFF9CA3AF), fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(14.dp))
                        val kpList = kpData
                        if (!kpList.isNullOrEmpty()) {
                            val latest = kpList.last()
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text("Index Level", style = MaterialTheme.typography.labelSmall, color = Color(0xFF9CA3AF))
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text("${latest.kp}", style = MaterialTheme.typography.headlineMedium, color = Color(0xFFFBBF24), fontWeight = FontWeight.Bold)
                                }
                                Text("Timestamp:\n${latest.timeTag}", style = MaterialTheme.typography.bodyMedium, color = Color(0xFF9CA3AF), textAlign = TextAlign.End)
                            }
                        } else {
                            Text("Syncing K-index telemetry...", style = MaterialTheme.typography.bodyLarge, color = Color(0xFF9CA3AF))
                        }
                    }
                }

                item {
                    WidgetCard {
                        Text("SOLAR WIND (RTSW)", style = MaterialTheme.typography.labelMedium, color = Color(0xFF9CA3AF), fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(14.dp))
                        val winds = windData
                        val latestWind = winds?.lastOrNull { it.speed != null } ?: winds?.lastOrNull()
                        if (latestWind != null) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text("Speed", style = MaterialTheme.typography.labelMedium, color = Color(0xFF9CA3AF))
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text("${latestWind.speed ?: "N/A"} km/s", style = MaterialTheme.typography.titleMedium, color = Color(0xFFF3F4F6), fontWeight = FontWeight.SemiBold)
                                }
                                Column(horizontalAlignment = Alignment.End) {
                                    Text("Density", style = MaterialTheme.typography.labelMedium, color = Color(0xFF9CA3AF))
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text("${latestWind.density ?: "N/A"} p/cm³", style = MaterialTheme.typography.titleMedium, color = Color(0xFFF3F4F6), fontWeight = FontWeight.SemiBold)
                                }
                            }
                        } else {
                            Text("Awaiting solar wind feed...", style = MaterialTheme.typography.bodyLarge, color = Color(0xFF9CA3AF))
                        }
                    }
                }

                item {
                    WidgetCard {
                        Text("SPACE WEATHER ALERTS", style = MaterialTheme.typography.labelMedium, color = Color(0xFF9CA3AF), fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(12.dp))
                        val alerts = alertsData
                        if (!alerts.isNullOrEmpty()) {
                            Text(
                                text = "${alerts.last().message?.take(160)}...",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color(0xFFF3F4F6)
                            )
                        } else {
                            Text("No active geomagnetic alerts.", style = MaterialTheme.typography.bodyLarge, color = Color(0xFF9CA3AF))
                        }
                    }
                }

                item {
                    WidgetCard {
                        Text("SPACEFLIGHT NEWS", style = MaterialTheme.typography.labelMedium, color = Color(0xFF9CA3AF), fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(16.dp))
                        val news = newsData
                        if (!news.isNullOrEmpty()) {
                            news.take(3).forEachIndexed { index, article ->
                                if (index > 0) {
                                    Spacer(modifier = Modifier.height(16.dp))
                                    HorizontalDivider(color = Color(0xFF2D3748), thickness = 1.dp)
                                    Spacer(modifier = Modifier.height(16.dp))
                                }
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            try {
                                                uriHandler.openUri(article.url)
                                            } catch (e: Exception) {}
                                        }
                                ) {
                                    if (article.imageUrl.isNotBlank()) {
                                        AsyncImage(
                                            model = article.imageUrl,
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(180.dp)
                                                .clip(RoundedCornerShape(16.dp))
                                        )
                                        Spacer(modifier = Modifier.height(12.dp))
                                    }
                                    Text(
                                        text = article.title,
                                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                                        color = Color(0xFF60A5FA),
                                        textDecoration = TextDecoration.Underline
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = article.news_site,
                                        style = MaterialTheme.typography.labelMedium,
                                        color = Color(0xFF9CA3AF)
                                    )
                                }
                            }
                        } else {
                            Text("Loading news feeds...", style = MaterialTheme.typography.bodyLarge, color = Color(0xFF9CA3AF))
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    WidgetCard {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = R.drawable.corn_icon,
                                contentDescription = "Corn Icon",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Corn Store Index",
                                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                                    color = Color(0xFF60A5FA),
                                    textDecoration = TextDecoration.Underline,
                                    modifier = Modifier.clickable {
                                        try {
                                            uriHandler.openUri("https://simoesctt.github.io/corn-store-index/#/")
                                        } catch (e: Exception) {}
                                    }
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Corn Store App",
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                                    color = Color(0xFF34D399),
                                    textDecoration = TextDecoration.Underline,
                                    modifier = Modifier.clickable {
                                        try {
                                            uriHandler.openUri("https://simoesctt.github.io/corn-store-index/#/app/com.simoesctt.cornstore")
                                        } catch (e: Exception) {}
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
