package com.jinnylee.contacts.domain.usecase

import com.jinnylee.contacts.domain.model.Contact
import com.jinnylee.contacts.domain.repository.MainRepository

class ToggleFavoriteUseCase(
    private val repository: MainRepository
) {
    suspend operator fun invoke(contact: Contact, isFavorite: Boolean) {
        if (isFavorite) {
            repository.removeFavorite(contact)
        } else {
            repository.toggleFavorite(contact)
        }
    }
}
