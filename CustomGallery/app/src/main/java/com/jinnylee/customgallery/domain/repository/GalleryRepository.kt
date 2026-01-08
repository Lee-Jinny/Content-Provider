package com.jinnylee.customgallery.domain.repository

import androidx.paging.PagingData
import com.jinnylee.customgallery.domain.model.GalleryImage
import kotlinx.coroutines.flow.Flow

interface GalleryRepository {
    fun getImages(): Flow<PagingData<GalleryImage>>
}