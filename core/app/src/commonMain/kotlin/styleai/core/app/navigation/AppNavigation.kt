package styleai.core.app.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class AppNavigation(val route: String) {
    @Serializable
    data object MainHomePage : AppNavigation("main_home")

    @Serializable
    data object CameraPage : AppNavigation("camera")

    @Serializable
    data object InteractiveSvgPage : AppNavigation("InteractiveSvgPage")
}

