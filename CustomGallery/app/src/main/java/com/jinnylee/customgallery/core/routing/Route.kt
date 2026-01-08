package com.jinnylee.customgallery.core.routing

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface Route : NavKey {
    @Serializable
    data object Gallery : Route

    @Serializable
    data object Detail : Route
}