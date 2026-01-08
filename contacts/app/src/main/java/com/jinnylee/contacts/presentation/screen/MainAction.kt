package com.jinnylee.contacts.presentation.screen

import com.jinnylee.contacts.domain.model.Contact

sealed interface MainAction {
    data class UpdateSearchQuery(val query: String) : MainAction
    data class Search(val query: String) : MainAction
    data class ToggleFavorite(val contact: Contact) : MainAction
}