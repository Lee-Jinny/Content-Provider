package com.jinnylee.customgallery.data.repository

import android.content.ContentResolver
import android.net.Uri
import androidx.exifinterface.media.ExifInterface
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jinnylee.customgallery.data.paging.GalleryPagingSource
import com.jinnylee.customgallery.domain.model.GalleryImage
import com.jinnylee.customgallery.domain.model.ImageExif
import com.jinnylee.customgallery.domain.repository.GalleryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    private val contentResolver: ContentResolver
) : GalleryRepository {

    override fun getImages(): Flow<PagingData<GalleryImage>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GalleryPagingSource(contentResolver) }
        ).flow
    }

    override suspend fun getExif(uri: Uri): ImageExif? {
        return withContext(Dispatchers.IO) {
            try {
                contentResolver.openInputStream(uri)?.use { inputStream ->
                    val exif = ExifInterface(inputStream)
                    val latLong = FloatArray(2)
                    val hasLatLong = exif.getLatLong(latLong)

                    ImageExif(
                        dateTime = exif.getAttribute(ExifInterface.TAG_DATETIME),
                        make = exif.getAttribute(ExifInterface.TAG_MAKE),
                        model = exif.getAttribute(ExifInterface.TAG_MODEL),
                        latitude = if (hasLatLong) latLong[0].toDouble() else null,
                        longitude = if (hasLatLong) latLong[1].toDouble() else null,
                        exposureTime = exif.getAttribute(ExifInterface.TAG_EXPOSURE_TIME),
                        iso = exif.getAttribute(ExifInterface.TAG_ISO_SPEED_RATINGS),
                        fNumber = exif.getAttribute(ExifInterface.TAG_F_NUMBER),
                        width = exif.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH, 0),
                        height = exif.getAttributeInt(ExifInterface.TAG_IMAGE_LENGTH, 0),
                        orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
                    )
                }
            } catch (e: Exception) {
                null
            }
        }
    }
}
