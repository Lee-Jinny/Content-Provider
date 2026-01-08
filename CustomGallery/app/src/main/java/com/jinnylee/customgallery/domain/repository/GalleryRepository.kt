package com.jinnylee.customgallery.domain.repository

import android.net.Uri
import androidx.paging.PagingData
import com.jinnylee.customgallery.domain.model.GalleryImage
import com.jinnylee.customgallery.domain.model.ImageExif
import kotlinx.coroutines.flow.Flow

interface GalleryRepository {
    fun getImages(): Flow<PagingData<GalleryImage>>
    suspend fun getExif(uri: Uri): ImageExif?
}