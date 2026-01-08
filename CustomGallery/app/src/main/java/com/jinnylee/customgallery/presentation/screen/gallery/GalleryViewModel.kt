package com.jinnylee.customgallery.presentation.screen.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jinnylee.customgallery.domain.model.GalleryImage
import com.jinnylee.customgallery.domain.repository.GalleryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val repository: GalleryRepository
) : ViewModel() {

    private val _state = MutableStateFlow(GalleryState())
    val state: StateFlow<GalleryState> = _state.asStateFlow()

    private val _events = MutableSharedFlow<GalleryEvent>()
    val events: SharedFlow<GalleryEvent> = _events.asSharedFlow()

    val images: Flow<PagingData<GalleryImage>> = repository.getImages()
        .cachedIn(viewModelScope)

    fun onAction(action: GalleryAction) {
        when (action) {
            is GalleryAction.OnImageClick -> {
                viewModelScope.launch {
                    _events.emit(GalleryEvent.NavigateToDetail(action.image))
                }
            }
            is GalleryAction.OnPermissionResult -> {
                _state.update { it.copy(hasPermission = action.isGranted) }
            }
        }
    }
}