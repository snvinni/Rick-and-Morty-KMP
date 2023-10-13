package core.designSystem

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import core.theme.Green
import feature.paginator.LoadingType

@Composable
fun PaginationLoading(
    loadingType: LoadingType,
    modifier: Modifier = Modifier
) {

    when (loadingType) {
        LoadingType.NextPage -> {
            LinearProgressIndicator(
                color = Green,
                modifier = modifier,
                strokeCap = StrokeCap.Round
            )
        }

        LoadingType.FirstPage -> {
            CircularProgressIndicator(
                color = Green,
                modifier = modifier,
                strokeWidth = 2.dp
            )
        }

        else -> Unit
    }
}