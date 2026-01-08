package com.jinnylee.customgallery.data.repository

import android.content.ContentResolver
import android.net.Uri
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jinnylee.customgallery.data.exif.ExifDataReader
import com.jinnylee.customgallery.data.media.MediaPagingSource
import com.jinnylee.customgallery.domain.model.GalleryImage
import com.jinnylee.customgallery.domain.model.ImageExif
import com.jinnylee.customgallery.domain.repository.GalleryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    private val contentResolver: ContentResolver,
    private val exifDataReader: ExifDataReader
) : GalleryRepository {

    override fun getImages(): Flow<PagingData<GalleryImage>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MediaPagingSource(contentResolver) }
        ).flow
    }

    override suspend fun getExif(uri: Uri): ImageExif? {
        return exifDataReader.readExif(uri)
    }
}
