package com.jinnylee.customgallery.domain.usecase

import android.net.Uri
import com.jinnylee.customgallery.domain.model.ImageExif
import com.jinnylee.customgallery.domain.repository.GalleryRepository
import javax.inject.Inject

class GetImageExifUseCase @Inject constructor(
    private val repository: GalleryRepository
) {
    suspend operator fun invoke(uri: Uri): ImageExif? {
        return repository.getExif(uri)
    }
}
