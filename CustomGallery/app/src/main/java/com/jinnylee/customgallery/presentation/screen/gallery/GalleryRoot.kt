package com.jinnylee.customgallery.presentation.screen.gallery

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.jinnylee.customgallery.domain.model.GalleryImage
import kotlinx.coroutines.flow.collectLatest

@Composable
fun GalleryRoot(
    onNavigateToDetail: (GalleryImage) -> Unit,
    viewModel: GalleryViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()
    val images = viewModel.images.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        viewModel.events.collectLatest { event ->
            when (event) {
                is GalleryEvent.NavigateToDetail -> onNavigateToDetail(event.image)
                is GalleryEvent.ShowError -> {
                    // Show snackbar or toast
                }
            }
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        viewModel.onAction(GalleryAction.OnPermissionResult(isGranted))
    }

    LaunchedEffect(Unit) {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
        permissionLauncher.launch(permission)
    }

        GalleryScreen(
            state = state,
            images = images,
            onAction = viewModel::onAction,
            onRequestPermission = {            val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                Manifest.permission.READ_MEDIA_IMAGES
            } else {
                Manifest.permission.READ_EXTERNAL_STORAGE
            }
            permissionLauncher.launch(permission)
        },
        modifier = modifier
    )
}