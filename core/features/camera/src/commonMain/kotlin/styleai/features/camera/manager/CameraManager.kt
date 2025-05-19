package styleai.features.camera.manager

import androidx.compose.runtime.Composable

@Composable
expect fun rememberCameraManager(
    onResult: (SharedImage?) -> Unit,
    onError: (String) -> Unit
): CameraManager


expect class CameraManager(
    onLaunch: () -> Unit
) {
    fun launch()
}