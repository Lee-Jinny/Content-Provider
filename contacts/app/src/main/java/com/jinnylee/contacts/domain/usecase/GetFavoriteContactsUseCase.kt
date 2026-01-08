package com.jinnylee.contacts.domain.usecase

import com.jinnylee.contacts.domain.model.Contact
import com.jinnylee.contacts.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteContactsUseCase(
    private val repository: MainRepository
) {
    operator fun invoke(): Flow<List<Contact>> {
        return repository.getFavoriteContacts()
    }
}
