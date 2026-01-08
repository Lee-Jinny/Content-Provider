package com.jinnylee.customgallery.presentation.screen.detail

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.jinnylee.customgallery.domain.model.GalleryImage
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DetailRoot(
    image: GalleryImage,
    onBack: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(image) {
        viewModel.setImage(image)
    }

    LaunchedEffect(Unit) {
        viewModel.events.collectLatest { event ->
            when (event) {
                DetailEvent.NavigateBack -> onBack()
                is DetailEvent.ShowError -> { /* Show error toast */ }
            }
        }
    }

    DetailScreen(
        state = state,
        onAction = { action ->
            when (action) {
                DetailAction.OnShareClick -> {
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "image/*"
                        putExtra(Intent.EXTRA_STREAM, image.uri)
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }
                    context.startActivity(Intent.createChooser(shareIntent, "Share Image"))
                }
                else -> viewModel.onAction(action)
            }
        },
        modifier = modifier
    )
}