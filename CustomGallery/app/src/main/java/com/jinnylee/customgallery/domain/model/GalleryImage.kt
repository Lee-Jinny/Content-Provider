package com.jinnylee.customgallery.domain.model

import android.net.Uri

data class GalleryImage(
    val id: Long,
    val uri: Uri,
    val name: String,
    val dateAdded: Long,
    val size: Long,
    val mimeType: String,
    val width: Int,
    val height: Int
)