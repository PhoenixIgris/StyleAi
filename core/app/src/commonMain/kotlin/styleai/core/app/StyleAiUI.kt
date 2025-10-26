package styleai.core.app

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.koinInject
import styleai.core.app.navigation.AppNavigation
import styleai.core.designSystem.theme.StyleAiTheme
import styleai.core.ui.components.toast.ToastHost
import styleai.features.camera.CameraScreen
import styleai.features.interactive_svg.InteractiveScreen
import styleai.features.interactive_svg.PanoramaImageView
import styleai.features.interactive_svg.SvgInteractionView
import styleai.features.main.MainHomeContainerScreen

@Composable
fun StyleAiUI() {
    StyleAiTheme {
        val navigator = rememberNavController()
        Box(Modifier.fillMaxSize()) {
            ToastHost {
                Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Icon(
                        imageVector = Icons.Default.ArrowCircleLeft,
                        "",
                        modifier = Modifier.size(50.dp).clickable {
                            navigator.popBackStack()
                        }
                    )
                    Text("Select ", fontSize = 24.sp, textAlign = TextAlign.Center)
                    Button(onClick = {
                        navigator.navigate(AppNavigation.InteractiveSvgPage)
                    }, modifier = Modifier.fillMaxWidth()) {
                        Text("Interactive Svg Screen", color = Color.White)
                    }
                    Button(onClick = {
                        navigator.navigate(AppNavigation.PanoramaViewPage)
                    }, modifier = Modifier.fillMaxWidth()) {
                        Text("Panorama Screen", color = Color.White)
                    }
                    Button(onClick = {
                        navigator.navigate(AppNavigation.CameraPage)
                    }, modifier = Modifier.fillMaxWidth()) {
                        Text("Image Screen", color = Color.White)
                    }
                    NavHost(
                        navController = navigator,
                        startDestination = AppNavigation.InteractiveSvgPage,
                        modifier = Modifier.fillMaxSize()
                    ) {

                        composable<AppNavigation.MainHomePage> {
                            MainHomeContainerScreen(koinInject())
                        }
                        composable<AppNavigation.InteractiveSvgPage> {
                            SvgInteractionView("https://images.unsplash.com/photo-1592194996308-7b43878e84a6?auto=format&fit=crop&w=800&q=80")
                        }

                        composable<AppNavigation.PanoramaViewPage> {
                            PanoramaImageView()
                        }
                        composable<AppNavigation.CameraPage> {
                            CameraScreen(koinInject())
                        }
                    }
                }

            }
        }
    }
}