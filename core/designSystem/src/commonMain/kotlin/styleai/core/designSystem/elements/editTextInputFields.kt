package styleai.core.designSystem.elements

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import styleai.core.designSystem.helper.PlaceholderTransformation
import styleai.core.designSystem.theme.Shapes
import styleai.core.designSystem.theme.StyleAiTheme.typography
import styleai.core.designSystem.theme.componentColors.InputColors.default

@Composable
fun DefaultOutlinedEditText(
    modifier: Modifier = Modifier,
    value: String,
    hint: String = "hint",
    keyboardOptions: KeyboardOptions,
    error: String? = null,
    visualTransformation: VisualTransformation = if (value.isEmpty())
        PlaceholderTransformation(hint)
    else VisualTransformation.None,
    leadingIconView: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable () -> Unit = {},
    isSingleLine: Boolean = true,
    isEnabled: Boolean = true,
    isFocusable: Boolean = true,
    isRequired: Boolean = false,
    shape: Shape = Shapes.small,
    onValueChanged: (String) -> Unit,
    title: String = "Title",
) {
    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = value,
            isError = error != null,
            onValueChange = onValueChanged,
            enabled = isEnabled,
            keyboardOptions = keyboardOptions,
            label = {
                Row {
                    Text(
                        text = title,
                        color = default.unfocusedTextColor,
                        style = typography.body.SmallNormal,
                    )
                    if (isRequired) {
                        Text(
                            text = " *",
                            color = default.errorLabelColor,
                            style = typography.body.SmallNormal,
                        )
                    }
                }

            },
            leadingIcon = leadingIconView,
            trailingIcon = trailingIcon,
            placeholder = {
                Text(
                    text = hint, style = typography.body.MediumNormal.copy(fontSize = 16.sp),
                    color = default.disabledLabelColor
                )
            },
            colors = default,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Transparent)
                .focusProperties {
                    canFocus = isFocusable
                },
            textStyle = typography.body.MediumNormal.copy(fontSize = 16.sp),
            singleLine = isSingleLine,
            shape = shape,
            visualTransformation = visualTransformation
        )

        AnimatedVisibility(visible = error != null) {
            Text(
                modifier = Modifier.padding(4.dp),
                text = error.orEmpty(),
                style = typography.label.LargeNormal.copy(
                    color = default.errorLabelColor
                )
            )
        }
    }
}


@Composable
fun DefaultTextField(
    modifier: Modifier = Modifier,
    value: String,
    hint: String = "hint",
    keyboardOptions: KeyboardOptions,
    error: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isSingleLine: Boolean = true,
    trailingIcon: DrawableResource? = null,
    trailingIconView: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    isEnabled: Boolean = true,
    shape: Shape = Shapes.medium,
    onValueChanged: (String) -> Unit,
    keyboardActions: KeyboardActions = KeyboardActions()
) {

    OutlinedTextField(
        value = value,
        isError = error != null,
        onValueChange = onValueChanged,
        enabled = isEnabled,
        keyboardOptions = keyboardOptions,
        placeholder = {
            Text(
                text = hint, style = typography.body.MediumNormal,
                color = default.disabledLabelColor
            )
        },
        leadingIcon = leadingIcon,
        trailingIcon = if (trailingIcon != null) {
            {
                Icon(
                    painter = painterResource(trailingIcon),
                    contentDescription = ""
                )
            }
        } else trailingIconView,
        modifier = modifier
            .fillMaxWidth()

            .background(color = Color.Transparent),
        textStyle = typography.body.MediumNormal.copy(fontSize = 16.sp),
        singleLine = isSingleLine,
        shape = shape,
        colors = default,
        visualTransformation = visualTransformation, keyboardActions = keyboardActions
    )
}


@Composable
fun DefaultOutlinedEditTextPassword(
    modifier: Modifier = Modifier,
    value: String,
    hint: String = "Hint",
    keyboardOptions: KeyboardOptions,
    error: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isSingleLine: Boolean = true,
    trailingIcon: @Composable () -> Unit,
    shape: Shape = Shapes.small,
    isRequired: Boolean = false,
    onValueChanged: (String) -> Unit,
    title: String = "Password"
) {
    Column(
        modifier = modifier
    ) {

        OutlinedTextField(
            value = value,
            isError = error != null,
            onValueChange = onValueChanged,
            keyboardOptions = keyboardOptions,
            label = {
                Row {
                    Text(
                        text = title,
                        color = default.unfocusedLabelColor,
                        style = typography.body.SmallNormal,
                    )
                    if (isRequired) {
                        Text(
                            text = " *",
                            color = default.errorLabelColor,
                            style = typography.body.SmallNormal,
                        )
                    }
                }
            },
            trailingIcon = {
                trailingIcon()
            },
            colors = default,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Transparent),
            textStyle = typography.body.MediumNormal.copy(fontSize = 16.sp),
            singleLine = isSingleLine,
            shape = shape,
            visualTransformation = if (value.isEmpty())
                PlaceholderTransformation(hint)
            else visualTransformation,

            )

        AnimatedVisibility(visible = error != null) {
            Text(
                modifier = Modifier.padding(4.dp),
                text = error.orEmpty(),
                style = typography.body.SmallNormal.copy(
                    color = default.errorLabelColor
                )
            )
        }
    }
}

