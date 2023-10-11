package core.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import core.theme.Green
import feature.paginator.LoadingType

@Composable
fun PaginationLoading(
    loadingType: LoadingType
) {
    when (loadingType) {
        LoadingType.NextPage -> {
            LinearProgressIndicator(
                color = Green,
                modifier = Modifier
                    .scale(0.5f)
                    .fillMaxSize()
            )
        }

        LoadingType.FirstPage -> {
            CircularProgressIndicator(
                color = Green,
                modifier = Modifier
                    .scale(0.2f)
                    .padding(16.dp)
                    .fillMaxSize()
            )
        }

        else -> Box(modifier = Modifier.size(0.dp))
    }
}