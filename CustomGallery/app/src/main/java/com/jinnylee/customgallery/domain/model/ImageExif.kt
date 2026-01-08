package com.jinnylee.customgallery.domain.model

data class ImageExif(
    val dateTime: String? = null,
    val make: String? = null,
    val model: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val exposureTime: String? = null,
    val iso: String? = null,
    val fNumber: String? = null,
    val width: Int = 0,
    val height: Int = 0,
    val orientation: Int = 0
)