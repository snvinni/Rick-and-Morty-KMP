package core.component

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.BrokenImage
import androidx.compose.material.icons.twotone.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import core.util.Resource
import core.extension.toImageBitmap
import core.util.toResource
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

private val httpClient by lazy { HttpClient() }

@Composable
fun ImageRequest(
    url: String,
    loading: ImageVector = Icons.TwoTone.Image,
    error: ImageVector =  Icons.TwoTone.BrokenImage,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null
) = ImageRequest(
    url = url,
    loading = rememberVectorPainter(loading),
    error = rememberVectorPainter(error),
    modifier = modifier,
    contentDescription = contentDescription,
    alignment = alignment,
    contentScale = contentScale,
    alpha = alpha,
    colorFilter = colorFilter
)

@Composable
fun ImageRequest(
    url: String,
    loading: Painter,
    error: Painter,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null
) {
    val resultState = remember {
        mutableStateOf<Resource<ImageBitmap, Throwable>>(Resource.Loading)
    }

    LaunchedEffect(url) {
        resultState.value = runCatching {
            httpClient.get(url)
                .readBytes()
                .toImageBitmap()
        }.toResource()
    }

    when (val result = resultState.value) {
        is Resource.Loading -> {
            Image(
                painter = loading,
                contentDescription = contentDescription,
                modifier = modifier,
                alignment = alignment,
                contentScale = contentScale,
                alpha = alpha,
                colorFilter = colorFilter
            )
        }

        is Resource.Result.Failure -> {
            Image(
                painter = error,
                contentDescription = contentDescription,
                modifier = modifier,
                alignment = alignment,
                contentScale = contentScale,
                alpha = alpha,
                colorFilter = colorFilter
            )
        }

        is Resource.Result.Success -> {
            Image(
                bitmap = result.data,
                contentDescription = contentDescription,
                modifier = modifier,
                alignment = alignment,
                contentScale = contentScale,
                alpha = alpha,
                colorFilter = colorFilter
            )
        }
    }
}