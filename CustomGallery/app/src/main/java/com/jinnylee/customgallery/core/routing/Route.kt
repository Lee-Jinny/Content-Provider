package com.jinnylee.customgallery.core.routing

import androidx.navigation3.runtime.NavKey
import com.jinnylee.customgallery.domain.model.GalleryImage
import kotlinx.serialization.Serializable

sealed interface Route : NavKey {
    @Serializable
    data object Gallery : Route

    @Serializable
    data class Detail(val image: GalleryImage) : Route
}