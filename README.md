# AstroDash

AstroDash is a native Android astronomy and space weather telemetry dashboard built with Jetpack Compose, Kotlin, Retrofit, and Material 3. It delivers real-time atmospheric and space science data streams optimized for modern Android displays.

## Features
- **Local Weather:** Live meteorological metrics and weather conditions for Singapore via Open-Meteo.
- **ISS Orbital Telemetry:** Real-time altitude, velocity, and geographic coordinates for the International Space Station.
- **Geomagnetic Kp Index:** Planetary K-index tracking via NOAA SWPC feeds.
- **Solar Wind (RTSW):** 1-minute solar wind proton speed and density telemetry.
- **Space Weather Alerts:** Active geomagnetic disturbance notifications.
- **Spaceflight News:** Curated space news feed with article thumbnails and direct external article links.
- **NASA TV Stream Link:** Quick-access link to official NASA video streams.
- **Corn Store Integration:** Dedicated branding and links to the Corn Store Index and Corn Store Android App.

## Tech Stack
- **UI:** Jetpack Compose, Material 3, LazyColumn widgets.
- **Networking:** Retrofit, OkHttp, Gson.
- **Image Loading:** Coil.
- **Platform:** Android (Targeting Oppo/ColorOS via Gradle & ADB).

## License
Distributed under the MIT License. See `LICENSE` for more information.
