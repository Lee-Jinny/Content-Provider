package com.jinnylee.customgallery.presentation.screen.gallery

import com.jinnylee.customgallery.domain.model.GalleryImage

sealed interface GalleryAction {
    data class OnImageClick(val image: GalleryImage) : GalleryAction
    data class OnPermissionResult(val isGranted: Boolean) : GalleryAction
}