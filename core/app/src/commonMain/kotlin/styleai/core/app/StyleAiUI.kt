package styleai.core.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.koinInject
import styleai.core.app.navigation.AppNavigation
import styleai.core.designSystem.theme.StyleAiTheme
import styleai.core.ui.components.toast.ToastHost
import styleai.features.camera.CameraScreen
import styleai.features.interactive_svg.InteractiveScreen
import styleai.features.main.MainHomeContainerScreen

@Composable
fun StyleAiUI() {
    StyleAiTheme {
        val navigator = rememberNavController()
        Box(Modifier.fillMaxSize()) {
            ToastHost {
                NavHost(
                    navController = navigator,
                    startDestination = AppNavigation.InteractiveSvgPage,
                    modifier = Modifier.fillMaxSize()
                ) {

                    composable<AppNavigation.MainHomePage> {
                        MainHomeContainerScreen(koinInject())
                    }
                    composable<AppNavigation.InteractiveSvgPage> {
                        InteractiveScreen(koinInject())
                    }
                    composable<AppNavigation.CameraPage> {
                        CameraScreen(koinInject())
                    }
                }
            }
        }
    }
}