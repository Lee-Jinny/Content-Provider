package com.jinnylee.customgallery.presentation.screen.detail

import com.jinnylee.customgallery.domain.model.GalleryImage
import com.jinnylee.customgallery.domain.model.ImageExif

data class DetailState(
    val image: GalleryImage? = null,
    val exif: ImageExif? = null,
    val isLoading: Boolean = false
)

sealed interface DetailAction {
    data object OnShareClick : DetailAction
    data object OnBackClick : DetailAction
}