package styleai.core.designSystem.elements

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import styleai.core.designSystem.theme.StyleAiTheme

@Composable
fun StyleAiPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    fullWidth: Boolean = true,
    trailingIcon: DrawableResource? = null,
) {
    val buttonModifier = if (fullWidth) modifier.fillMaxWidth() else modifier

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = buttonModifier.height(56.dp),
        shape = StyleAiTheme.shapes.button,
        colors = ButtonDefaults.buttonColors(
            containerColor = StyleAiTheme.colors.warmBrown,
            contentColor = StyleAiTheme.colors.creamBackground,
            disabledContainerColor = StyleAiTheme.colors.warmBrown.copy(alpha = 0.38f),
            disabledContentColor = StyleAiTheme.colors.creamBackground.copy(alpha = 0.72f),
        ),
        contentPadding = PaddingValues(horizontal = StyleAiTheme.spacing.lg),
    ) {
        Text(
            text = text,
            style = StyleAiTheme.typography.button,
            color = StyleAiTheme.colors.creamBackground,
        )
        Spacer(Modifier.width(StyleAiTheme.spacing.sm))
        if (trailingIcon != null) {
            Icon(
                painter = painterResource(trailingIcon),
                contentDescription = null,
                modifier = Modifier.size(18.dp),
            )
        } else {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                modifier = Modifier.size(18.dp),
            )
        }
    }
}

@Composable
fun StyleAiSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    fullWidth: Boolean = true,
    transparent: Boolean = true,
) {
    val buttonModifier = if (fullWidth) modifier.fillMaxWidth() else modifier

    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        modifier = buttonModifier.height(52.dp),
        shape = StyleAiTheme.shapes.button,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (transparent) Color.Transparent else StyleAiTheme.colors.cardCream,
            contentColor = StyleAiTheme.colors.warmBrown,
            disabledContentColor = StyleAiTheme.colors.warmBrown.copy(alpha = 0.42f),
        ),
        border = BorderStroke(1.dp, StyleAiTheme.colors.warmBrown),
        contentPadding = PaddingValues(horizontal = StyleAiTheme.spacing.lg),
    ) {
        Text(
            text = text,
            style = StyleAiTheme.typography.button,
            color = StyleAiTheme.colors.warmBrown,
        )
    }
}

@Composable
fun StyleAiTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    trailingIcon: Boolean = true,
) {
    TextButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.height(44.dp),
        colors = ButtonDefaults.textButtonColors(
            contentColor = StyleAiTheme.colors.warmBrown,
            disabledContentColor = StyleAiTheme.colors.warmBrown.copy(alpha = 0.42f),
        ),
        contentPadding = PaddingValues(horizontal = StyleAiTheme.spacing.md),
    ) {
        Text(
            text = text,
            style = StyleAiTheme.typography.button,
            color = StyleAiTheme.colors.warmBrown,
        )
        if (trailingIcon) {
            Spacer(Modifier.width(StyleAiTheme.spacing.sm))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                modifier = Modifier.size(17.dp),
            )
        }
    }
}

@Composable
fun StyleAiCard(
    modifier: Modifier = Modifier,
    shape: Shape = StyleAiTheme.shapes.card,
    backgroundColor: Color = StyleAiTheme.colors.cardCream,
    borderColor: Color? = StyleAiTheme.colors.softBorder,
    elevation: Dp = StyleAiTheme.shadows.card,
    contentPadding: PaddingValues = PaddingValues(StyleAiTheme.spacing.md),
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = modifier,
        shape = shape,
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        border = borderColor?.let { BorderStroke(1.dp, it) },
    ) {
        Column(
            modifier = Modifier.padding(contentPadding),
            content = content,
        )
    }
}

@Composable
fun StyleAiChoiceChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: DrawableResource? = null,
    minHeight: Dp = 72.dp,
) {
    val backgroundColor = if (selected) {
        StyleAiTheme.colors.blushPink
    } else {
        StyleAiTheme.colors.cardCream
    }
    val borderColor = if (selected) {
        StyleAiTheme.colors.blushPinkDark
    } else {
        StyleAiTheme.colors.softBorder
    }

    StyleAiCard(
        modifier = modifier
            .height(minHeight)
            .clickable(onClick = onClick),
        backgroundColor = backgroundColor,
        borderColor = borderColor,
        elevation = if (selected) StyleAiTheme.shadows.soft else 0.dp,
        contentPadding = PaddingValues(
            horizontal = StyleAiTheme.spacing.md,
            vertical = StyleAiTheme.spacing.sm,
        ),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (icon != null) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified,
                )
                Spacer(Modifier.width(StyleAiTheme.spacing.sm))
            }
            Text(
                text = text,
                style = StyleAiTheme.typography.bodyText,
                color = StyleAiTheme.colors.warmBrown,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun StyleAiProgressHeader(
    currentStep: Int,
    totalSteps: Int,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = StyleAiTheme.colors.warmBrown,
                )
            }
            Text(
                text = "Question $currentStep of $totalSteps",
                modifier = Modifier.weight(1f),
                style = StyleAiTheme.typography.caption,
                color = StyleAiTheme.colors.warmBrown,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.size(48.dp))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = StyleAiTheme.spacing.md),
            horizontalArrangement = Arrangement.spacedBy(StyleAiTheme.spacing.xs),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            repeat(totalSteps) { index ->
                val active = index < currentStep
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(4.dp)
                        .background(
                            color = if (active) {
                                StyleAiTheme.colors.blushPinkDark
                            } else {
                                StyleAiTheme.colors.softBorder
                            },
                            shape = CircleShape,
                        ),
                )
            }
        }
    }
}

@Composable
fun StyleAiResultCard(
    title: String,
    modifier: Modifier = Modifier,
    body: String? = null,
    bullets: List<String> = emptyList(),
    icon: DrawableResource? = null,
    showChevron: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    val clickableModifier = if (onClick != null) {
        modifier.clickable(onClick = onClick)
    } else {
        modifier
    }

    StyleAiCard(
        modifier = clickableModifier.fillMaxWidth(),
        contentPadding = PaddingValues(StyleAiTheme.spacing.md),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(StyleAiTheme.spacing.md),
        ) {
            StyleAiIconArea(icon = icon)
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = StyleAiTheme.typography.sectionTitle,
                    color = StyleAiTheme.colors.darkBrownText,
                )
                if (!body.isNullOrBlank()) {
                    Spacer(Modifier.height(StyleAiTheme.spacing.xs))
                    Text(
                        text = body,
                        style = StyleAiTheme.typography.bodyText,
                        color = StyleAiTheme.colors.warmBrown,
                    )
                }
                bullets.forEach { bullet ->
                    Spacer(Modifier.height(StyleAiTheme.spacing.xs))
                    Text(
                        text = "- $bullet",
                        style = StyleAiTheme.typography.bodyText,
                        color = StyleAiTheme.colors.warmBrown,
                    )
                }
            }
            if (showChevron) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = StyleAiTheme.colors.warmBrown,
                )
            }
        }
    }
}

@Composable
fun StyleAiSummaryCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    icon: DrawableResource? = null,
) {
    StyleAiCard(
        modifier = modifier.fillMaxWidth(),
        elevation = StyleAiTheme.shadows.soft,
        contentPadding = PaddingValues(StyleAiTheme.spacing.md),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(StyleAiTheme.spacing.md),
        ) {
            StyleAiIconArea(icon = icon, size = 44.dp, iconSize = 24.dp)
            Column {
                Text(
                    text = title,
                    style = StyleAiTheme.typography.caption,
                    color = StyleAiTheme.colors.warmBrown.copy(alpha = 0.78f),
                )
                Spacer(Modifier.height(StyleAiTheme.spacing.xs))
                Text(
                    text = value,
                    style = StyleAiTheme.typography.sectionTitle,
                    color = StyleAiTheme.colors.darkBrownText,
                )
            }
        }
    }
}

@Composable
fun StyleAiLoadingDots(
    modifier: Modifier = Modifier,
    dotCount: Int = 5,
) {
    val transition = rememberInfiniteTransition(label = "styleAiLoadingDots")

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(StyleAiTheme.spacing.sm),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(dotCount) { index ->
            val alpha by transition.animateFloat(
                initialValue = 0.35f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 520, delayMillis = index * 110),
                    repeatMode = RepeatMode.Reverse,
                ),
                label = "styleAiLoadingDot$index",
            )
            Box(
                modifier = Modifier
                    .size(if (index == dotCount / 2) 9.dp else 8.dp)
                    .alpha(alpha)
                    .background(
                        color = if (index == dotCount / 2) {
                            StyleAiTheme.colors.warmBrown
                        } else {
                            StyleAiTheme.colors.blushPinkDark
                        },
                        shape = CircleShape,
                    ),
            )
        }
    }
}

@Composable
fun StyleAiTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null,
    trailingIcon: DrawableResource? = null,
    onTrailingClick: (() -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (onBack != null) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = StyleAiTheme.colors.warmBrown,
                )
            }
        } else {
            Spacer(Modifier.size(48.dp))
        }
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = StyleAiTheme.typography.sectionTitle,
            color = StyleAiTheme.colors.darkBrownText,
            textAlign = TextAlign.Center,
        )
        if (trailingIcon != null) {
            IconButton(onClick = { onTrailingClick?.invoke() }) {
                Icon(
                    painter = painterResource(trailingIcon),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
            }
        } else {
            Spacer(Modifier.size(48.dp))
        }
    }
}

@Composable
fun StyleAiScreenContainer(
    modifier: Modifier = Modifier,
    scrollable: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(StyleAiTheme.spacing.lg),
    content: @Composable ColumnScope.() -> Unit,
) {
    val safePadding = WindowInsets.safeDrawing.asPaddingValues()
    val scrollModifier = if (scrollable) {
        Modifier.verticalScroll(rememberScrollState())
    } else {
        Modifier
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(StyleAiTheme.colors.creamBackground)
            .padding(safePadding)
            .then(scrollModifier)
            .padding(contentPadding),
        content = content,
    )
}

@Composable
private fun RowScope.StyleAiIconArea(
    icon: DrawableResource?,
    size: Dp = 56.dp,
    iconSize: Dp = 32.dp,
) {
    Box(
        modifier = Modifier
            .size(size)
            .background(StyleAiTheme.colors.lightBeige, StyleAiTheme.shapes.chip)
            .border(1.dp, StyleAiTheme.colors.softBorder, StyleAiTheme.shapes.chip),
        contentAlignment = Alignment.Center,
    ) {
        if (icon != null) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(iconSize),
                tint = Color.Unspecified,
            )
        } else {
            Box(
                modifier = Modifier
                    .size(iconSize)
                    .background(StyleAiTheme.colors.mutedGold, CircleShape),
            )
        }
    }
}
