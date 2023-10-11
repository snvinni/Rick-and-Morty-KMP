package core.designSystem

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle

@Composable
fun SimpleBadge(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(25),
    textStyle: TextStyle = BadgeStyle,
    content: @Composable () -> Unit
) = Card(
    modifier,
    shape
) {
   CompositionLocalProvider(LocalTextStyle provides textStyle) {
       content()
   }
}