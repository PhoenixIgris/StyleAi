package styleai.features.camera.customview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Camera
import androidx.compose.material.icons.outlined.Photo
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import styleai.core.designSystem.elements.DefaultButton
import styleai.core.designSystem.elements.DefaultDivider
import styleai.core.designSystem.theme.StyleAiTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImagePicker(
    closeRequest: (Boolean) -> Unit, cameraLaunched: () -> Unit, galleryLaunched: () -> Unit
) {

    BasicAlertDialog(
        onDismissRequest = { closeRequest(false) },
        content = {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.clip(StyleAiTheme.shapes.medium).background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Select Image",
                    style = StyleAiTheme.typography.display.SmallRegular.copy(
                        fontSize = 20.sp
                    ),
                    textAlign = TextAlign.Start
                )


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clip(StyleAiTheme.shapes.small)
                        .clickable(onClick = cameraLaunched).padding(10.dp)
                ) {

                    Icon(
                        imageVector = Icons.Outlined.Camera,
                        contentDescription = "",
                        modifier = Modifier.size(width = 21.dp, height = 18.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Camera",
                        style = StyleAiTheme.typography.body.MediumNormal,
                        fontSize = 14.sp,
                        modifier = Modifier.fillMaxWidth().wrapContentHeight()
                            .padding(bottom = 6.dp, top = 6.dp),
                        color = StyleAiTheme.colors.onBackground.copy(alpha = 0.7f),
                        textAlign = TextAlign.Start,
                    )
                }

                DefaultDivider()

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clip(StyleAiTheme.shapes.small)
                        .clickable(onClick = galleryLaunched).padding(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Photo,
                        contentDescription = "",
                        modifier = Modifier.size(width = 21.dp, height = 18.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Choose from Library",
                        style = StyleAiTheme.typography.body.MediumNormal,
                        fontSize = 14.sp,
                        modifier = Modifier.fillMaxWidth().wrapContentHeight()
                            .padding(bottom = 6.dp, top = 6.dp),
                        color = StyleAiTheme.colors.onBackground.copy(alpha = 0.7f),
                        textAlign = TextAlign.Start,
                    )
                }

                DefaultButton(
                    modifier = Modifier.padding(top = 24.dp).fillMaxWidth(),
                    label = "Cancel",
                ) {
                    closeRequest(false)
                }
            }
        },
        modifier = Modifier.fillMaxWidth(),
    )
}

