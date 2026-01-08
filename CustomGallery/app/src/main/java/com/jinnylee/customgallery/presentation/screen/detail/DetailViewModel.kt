package com.jinnylee.customgallery.presentation.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jinnylee.customgallery.domain.model.GalleryImage
import com.jinnylee.customgallery.domain.usecase.GetImageExifUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getImageExifUseCase: GetImageExifUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state.asStateFlow()

    private val _events = MutableSharedFlow<DetailEvent>()
    val events: SharedFlow<DetailEvent> = _events.asSharedFlow()

    fun setImage(image: GalleryImage) {
        _state.update { it.copy(image = image, isLoading = true) }
        viewModelScope.launch {
            val exif = getImageExifUseCase(image.uri)
            _state.update { it.copy(exif = exif, isLoading = false) }
        }
    }

    fun onAction(action: DetailAction) {
        when (action) {
            is DetailAction.OnBackClick -> {
                viewModelScope.launch {
                    _events.emit(DetailEvent.NavigateBack)
                }
            }
            is DetailAction.OnShareClick -> {
                // 공유는 UI 레벨에서 Context가 필요하므로 Root에서 처리하거나
                // 별도의 이벤트를 던질 수 있습니다.
            }
        }
    }
}