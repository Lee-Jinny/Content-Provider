package com.jinnylee.contacts.domain.usecase

import com.jinnylee.contacts.domain.model.Contact
import com.jinnylee.contacts.domain.repository.MainRepository
import javax.inject.Inject

class GetContactsUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(): List<Contact> {
        return repository.getContacts()
    }
}
