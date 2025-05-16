package styleai.core.ui.customview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import styleai.core.designSystem.theme.StyleAiTheme
import styleai.core.resources.Res
import styleai.core.resources.loading
import styleai.core.ui.generated.resources.ic_info
import styleai.core.ui.generated.resources.ic_info_critical

@Composable
fun LoadingDialog(
    message: String = stringResource(Res.string.loading),
    isDismissible: Boolean = false,
    onDismissRequest: (Boolean) -> Unit = {}
) {
    Dialog(
        onDismissRequest = {
            if (isDismissible) onDismissRequest(false)
        },
        DialogProperties(
            dismissOnBackPress = isDismissible,
            dismissOnClickOutside = isDismissible,
            usePlatformDefaultWidth = false
        )
    ) {
        LoadingView(message)

    }
}


@Composable
fun LoadingView(message: String) {
    val isError = false
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                if (isError) Color.Red.copy(
                    alpha = 0.15f
                ) else Color.Transparent
            )

    ) {
        if (isError) {
            Image(
                painter = painterResource(styleai.core.ui.generated.resources.Res.drawable.ic_info_critical),
                contentDescription = "",
                modifier = Modifier
                    .height(height = 93.dp)
                    .padding(bottom = 25.dp),
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                painter = painterResource(styleai.core.ui.generated.resources.Res.drawable.ic_info),
                contentDescription = "",
                modifier = Modifier
                    .height(height = 93.dp)
                    .padding(bottom = 25.dp),
                contentScale = ContentScale.Fit
            )
        }
        Text(
            text = message,
            textAlign = TextAlign.Center,
            color = Color.White,
            style = StyleAiTheme.typography.body.LargeSemiBold,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()

        )

    }
}
