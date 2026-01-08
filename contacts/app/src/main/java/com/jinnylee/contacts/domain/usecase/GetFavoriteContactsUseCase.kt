package com.jinnylee.contacts.domain.usecase

import com.jinnylee.contacts.domain.model.Contact
import com.jinnylee.contacts.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteContactsUseCase @Inject constructor(
    private val repository: MainRepository
) {
    operator fun invoke(): Flow<List<Contact>> {
        return repository.getFavoriteContacts()
    }
}
