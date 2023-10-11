package core.designSystem

import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

val SubTitle
    @Composable get() = typography.subtitle1.copy(
        fontWeight = FontWeight.SemiBold
    )

val Body
    @Composable get() = typography.body1

val BadgeStyle
    @Composable get() = typography.caption