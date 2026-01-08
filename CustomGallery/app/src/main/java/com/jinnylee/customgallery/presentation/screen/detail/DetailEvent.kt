package com.jinnylee.customgallery.presentation.screen.detail

sealed interface DetailEvent {
    data object NavigateBack : DetailEvent
    data class ShowError(val message: String) : DetailEvent
}
