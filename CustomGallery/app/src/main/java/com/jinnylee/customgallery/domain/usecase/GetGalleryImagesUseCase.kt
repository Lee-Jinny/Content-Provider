package com.jinnylee.customgallery.domain.usecase

import androidx.paging.PagingData
import com.jinnylee.customgallery.domain.model.GalleryImage
import com.jinnylee.customgallery.domain.repository.GalleryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGalleryImagesUseCase @Inject constructor(
    private val repository: GalleryRepository
) {
    operator fun invoke(): Flow<PagingData<GalleryImage>> {
        return repository.getImages()
    }
}
