package com.jinnylee.customgallery.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.jinnylee.customgallery.domain.model.GalleryImage

@Composable
fun ImageGridItem(
    image: GalleryImage,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(image.uri)
            .crossfade(true)
            .build(),
        contentDescription = image.name,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { onClick() },
        contentScale = ContentScale.Crop
    )
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
private fun ImageGridItemPreview() {
    com.jinnylee.customgallery.ui.theme.CustomGalleryTheme {
        ImageGridItem(
            image = com.jinnylee.customgallery.domain.model.GalleryImage(
                id = 1L,
                uri = android.net.Uri.EMPTY,
                name = "Test Image",
                dateAdded = 0L,
                size = 1024L,
                mimeType = "image/jpeg",
                width = 100,
                height = 100
            ),
            onClick = {}
        )
    }
}