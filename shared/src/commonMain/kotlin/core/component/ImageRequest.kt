package core.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.BrokenImage
import androidx.compose.material.icons.twotone.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.RenderVectorGroup
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import core.extension.toImageBitmap
import core.util.Resource
import core.util.toResource
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

private val httpClient by lazy { HttpClient() }

@Composable
fun ImageRequest(
    url: String,
    failure: Painter = rememberVectorPainter(
        image = Icons.TwoTone.BrokenImage,
    ),
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    contentDescription: String? = null,
    colorFilter: ColorFilter? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
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
            Box(
                modifier = modifier.semantics {
                    contentDescription?.let {
                        this.contentDescription = it
                    }
                },
                contentAlignment = alignment,
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    strokeWidth = 2.dp
                )
            }
        }

        is Resource.Result.Failure -> {
            Image(
                painter = failure,
                contentDescription = contentDescription,
                modifier = modifier.sizeIn(
                    maxHeight = 24.dp,
                    maxWidth = 24.dp
                ),
                alignment = alignment,
                colorFilter = colorFilter,
                contentScale = contentScale,
                alpha = alpha,
            )
        }

        is Resource.Result.Success -> {
            Image(
                bitmap = result.data,
                contentDescription = contentDescription,
                modifier = modifier.clip(shape),
                alignment = alignment,
                colorFilter = colorFilter,
                contentScale = contentScale,
                alpha = alpha,
            )
        }
    }
}


@Composable
fun rememberVectorPainter(
    image: ImageVector,
    tintColor: Color
) = rememberVectorPainter(
    defaultWidth = image.defaultWidth,
    defaultHeight = image.defaultHeight,
    viewportWidth = image.viewportWidth,
    viewportHeight = image.viewportHeight,
    name = image.name,
    tintColor = tintColor,
    tintBlendMode = image.tintBlendMode,
    autoMirror = image.autoMirror,
    content = { _, _ -> RenderVectorGroup(group = image.root) }
)