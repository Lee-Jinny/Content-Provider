package com.jinnylee.customgallery.data.media

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jinnylee.customgallery.domain.model.GalleryImage

class MediaPagingSource(
    private val mediaStoreDataSource: MediaStoreDataSource
) : PagingSource<Int, GalleryImage>() {

    override fun getRefreshKey(state: PagingState<Int, GalleryImage>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GalleryImage> {
        return try {
            val offset = params.key ?: 0
            val limit = params.loadSize
            
            val imageList = mediaStoreDataSource.getImages(limit, offset)

            LoadResult.Page(
                data = imageList,
                prevKey = if (offset == 0) null else offset - limit,
                nextKey = if (imageList.size < limit) null else offset + limit
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}