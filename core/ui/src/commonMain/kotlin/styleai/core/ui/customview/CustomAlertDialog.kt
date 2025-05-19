package styleai.core.ui.customview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import styleai.core.designSystem.elements.DefaultButton
import styleai.core.designSystem.elements.DefaultOutLineButton
import styleai.core.designSystem.theme.StyleAiTheme
import styleai.core.resources.cancel
import styleai.core.resources.proceed
import styleai.core.ui.generated.resources.Res
import styleai.core.ui.generated.resources.ic_close


@Composable
fun CustomAlertDialog(
    title: String = "Welcome",
    desc: String = "Hello, This is StyleAi",
    isDismissible: Boolean = false, onDismissRequest: (Boolean) -> Unit = {}
) {
    Dialog(
        onDismissRequest = {
            if (isDismissible) onDismissRequest(false)
        }, DialogProperties(
            dismissOnBackPress = isDismissible,
            usePlatformDefaultWidth = false,
            dismissOnClickOutside = isDismissible
        )
    ) {
        CustomAlertDialogView(
            title, desc,
            Modifier.wrapContentSize(), onDismiss = onDismissRequest
        )

    }
}


@Composable
fun CustomAlertDialogView(
    title: String,
    desc: String,
    modifier: Modifier, onDismiss: (Boolean) -> Unit
) {


    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = modifier.width(500.dp).clip(StyleAiTheme.shapes.medium)
            .background(Color.White)
            .padding(12.dp)

    ) {

        Image(
            painter = painterResource(Res.drawable.ic_close),
            contentDescription = "",
            modifier = Modifier.wrapContentSize().clickable {
                onDismiss(false)
            }.align(Alignment.End),
            contentScale = ContentScale.Fit
        )
        Text(
            text = title,
            textAlign = TextAlign.Start,
            color = StyleAiTheme.colors.error,
            style = StyleAiTheme.typography.heading.DefaultSemiBold,
            modifier = Modifier.fillMaxWidth().align(Alignment.Start).padding(
                start = 15.dp
            )
        )
        Text(
            text = desc,
            style = StyleAiTheme.typography.body.MediumNormal,
            fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth().padding(15.dp),
            textAlign = TextAlign.Start
        )

        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 10.dp, start = 15.dp, end = 15.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            DefaultOutLineButton(
                modifier = Modifier.fillMaxWidth(0.5f),
                label = stringResource(styleai.core.resources.Res.string.cancel),
            ) {
                onDismiss(false)
            }

            DefaultButton(
                modifier = Modifier.fillMaxWidth(1f),
                label = stringResource(styleai.core.resources.Res.string.proceed),
            ) {
                onDismiss(true)
            }
        }
    }
}



