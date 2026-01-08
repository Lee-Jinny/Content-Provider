package com.jinnylee.customgallery.presentation.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.jinnylee.customgallery.domain.model.ImageExif
import com.jinnylee.customgallery.presentation.component.ExifInfoItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    state: DetailState,
    onAction: (DetailAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = state.image?.name ?: "Detail") },
                navigationIcon = {
                    IconButton(onClick = { onAction(DetailAction.OnBackClick) }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { onAction(DetailAction.OnShareClick) }) {
                        Icon(Icons.Default.Share, contentDescription = "Share")
                    }
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            state.image?.let { image ->
                AsyncImage(
                    model = image.uri,
                    contentDescription = image.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Image Information", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))

                    ExifInfoItem(label = "Date", value = state.exif?.dateTime)
                    ExifInfoItem(label = "Maker", value = state.exif?.make)
                    ExifInfoItem(label = "Model", value = state.exif?.model)
                    ExifInfoItem(
                        label = "Resolution",
                        value = state.exif?.let { "${it.width} x ${it.height}" })
                    ExifInfoItem(label = "ISO", value = state.exif?.iso)
                    ExifInfoItem(label = "Aperture", value = state.exif?.fNumber)
                    ExifInfoItem(label = "Exposure", value = state.exif?.exposureTime)
                    ExifInfoItem(label = "Location", value = state.exif?.let {
                        if (it.latitude != null && it.longitude != null) {
                            "%.4f, %.4f".format(it.latitude, it.longitude)
                        } else null
                    })
                }
            }
        }
    }
}