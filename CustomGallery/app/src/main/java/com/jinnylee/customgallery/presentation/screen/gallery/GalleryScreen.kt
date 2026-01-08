package com.jinnylee.customgallery.presentation.screen.gallery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.jinnylee.customgallery.domain.model.GalleryImage
import com.jinnylee.customgallery.presentation.component.ImageGridItem
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun GalleryScreen(
    state: GalleryState,
    images: LazyPagingItems<GalleryImage>,
    onAction: (GalleryAction) -> Unit,
    onRequestPermission: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        if (!state.hasPermission) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = onRequestPermission) {
                    Text(text = "Grant Permission")
                }
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(1.dp),
                horizontalArrangement = Arrangement.spacedBy(1.dp),
                verticalArrangement = Arrangement.spacedBy(1.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    count = images.itemCount,
                    key = { index -> images[index]?.id ?: index }
                ) { index ->
                    val image = images[index]
                    if (image != null) {
                        ImageGridItem(
                            image = image,
                            onClick = { onAction(GalleryAction.OnImageClick(image)) }
                        )
                    }
                }
            }

            if (images.loadState.refresh is LoadState.Loading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun GalleryScreenPermissionPreview() {
    com.jinnylee.customgallery.ui.theme.CustomGalleryTheme {
        // PagingItems를 프리뷰에서 완벽하게 모킹하기는 어렵지만, 
        // 권한이 없는 상태의 UI는 아래와 같이 확인할 수 있습니다.
        val emptyFlow = kotlinx.coroutines.flow.flowOf(androidx.paging.PagingData.empty<GalleryImage>())
        GalleryScreen(
            state = GalleryState(hasPermission = false),
            images = emptyFlow.collectAsLazyPagingItems(),
            onAction = {},
            onRequestPermission = {}
        )
    }
}