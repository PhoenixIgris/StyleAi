package styleai.core.network

object StyleApiConfig {
    const val androidEmulatorBaseUrl = "http://10.0.2.2:8085"
    const val localBaseUrl = "http://localhost:8085"
}

expect fun defaultStyleApiBaseUrl(): String

