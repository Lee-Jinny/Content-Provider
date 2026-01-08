package com.jinnylee.customgallery.presentation.screen.gallery

import com.jinnylee.customgallery.domain.model.GalleryImage

sealed interface GalleryEvent {
    data class NavigateToDetail(val image: GalleryImage) : GalleryEvent
    data class ShowError(val message: String) : GalleryEvent
}