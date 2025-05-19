package styleai.features.camera


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import styleai.core.designSystem.elements.DefaultOutLineButton
import styleai.core.features.camera.generated.resources.Res
import styleai.core.features.camera.generated.resources.permission_denied
import styleai.core.features.camera.generated.resources.please_grant_this_permission
import styleai.core.resources.img_person_placeholder
import styleai.core.resources.res
import styleai.core.ui.components.toast.LocalToastManager
import styleai.core.ui.components.toast.toastError
import styleai.core.ui.customview.CustomAlertDialog
import styleai.core.ui.customview.LoadingDialog
import styleai.features.camera.customview.ImagePicker
import styleai.features.camera.manager.PermissionCallback
import styleai.features.camera.manager.PermissionStatus
import styleai.features.camera.manager.PermissionType
import styleai.features.camera.manager.createPermissionsManager
import styleai.features.camera.manager.rememberCameraManager
import styleai.features.camera.manager.rememberGalleryManager


@Composable
fun CameraScreen(
    viewModel: CameraScreenViewModel,
) {
    val state by viewModel.state.collectAsState()

    val localToastManager = LocalToastManager.current

    LaunchedEffect(viewModel.toast) {
        viewModel.toast.collect { event ->
            event.actUpOn {
                localToastManager.post(it)
            }
        }
    }

    if (state.loading) {
        LoadingDialog(message = stringResource(state.loadingMessage))
    }
    val coroutineScope = rememberCoroutineScope()
    var imageSourceOptionDialog by remember { mutableStateOf(value = false) }
    var launchCamera by remember { mutableStateOf(value = false) }
    var launchGallery by remember { mutableStateOf(value = false) }
    var launchSetting by remember { mutableStateOf(value = false) }
    var permissionRationalDialog by remember { mutableStateOf(value = false) }
    val permissionsManager = createPermissionsManager(object : PermissionCallback {
        override fun onPermissionStatus(
            permissionType: PermissionType, status: PermissionStatus
        ) {
            when (status) {
                PermissionStatus.GRANTED -> {
                    when (permissionType) {
                        PermissionType.CAMERA -> launchCamera = true
                        PermissionType.GALLERY -> launchGallery = true
                    }
                }

                else -> {
                    permissionRationalDialog = true
                }
            }
        }


    })
    val cameraManager = rememberCameraManager(onResult = {
        coroutineScope.launch {
            if (it != null) {
                viewModel.setImage(it)
            }
        }
    }, onError = {
        viewModel.toastError(it)
    }

    )

    val galleryManager = rememberGalleryManager {
        coroutineScope.launch {
            if (it != null) {
                viewModel.setImage(it)
            }
        }
    }
    if (imageSourceOptionDialog) {
        ImagePicker(closeRequest = {
            imageSourceOptionDialog = false
        }, galleryLaunched = {
            imageSourceOptionDialog = false
            launchGallery = true
        }, cameraLaunched = {
            imageSourceOptionDialog = false
            launchCamera = true
        })
    }
    if (launchGallery) {
        if (permissionsManager.isPermissionGranted(PermissionType.GALLERY)) {
            galleryManager.launch()
        } else {
            permissionsManager.askPermission(PermissionType.GALLERY)
        }
        launchGallery = false
    }
    if (launchCamera) {
        if (permissionsManager.isPermissionGranted(PermissionType.CAMERA)) {
            cameraManager.launch()
        } else {
            permissionsManager.askPermission(PermissionType.CAMERA)
        }
        launchCamera = false
    }
    if (launchSetting) {
        permissionsManager.launchSettings()
        launchSetting = false
    }
    if (permissionRationalDialog) {
        CustomAlertDialog(
            title = Res.string.permission_denied.res,
            desc = Res.string.please_grant_this_permission.res,
            onDismissRequest = {
                permissionRationalDialog = false
                if (it) {
                    launchSetting = true
                }
            })

    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        state.image?.toImageBitmap()?.let {
            Image(
                bitmap = it,
                contentDescription = "",
                modifier = Modifier.size(150.dp).clip(CircleShape),
                contentScale = ContentScale.Crop,
            )
        } ?: Image(
            painter = painterResource(styleai.core.resources.Res.drawable.img_person_placeholder),
            contentDescription = "",
            modifier = Modifier.size(150.dp).clip(CircleShape),
            contentScale = ContentScale.Crop,
        )
        DefaultOutLineButton(
            label = "Upload Image"
        ) {
            imageSourceOptionDialog = true
        }


    }

}

