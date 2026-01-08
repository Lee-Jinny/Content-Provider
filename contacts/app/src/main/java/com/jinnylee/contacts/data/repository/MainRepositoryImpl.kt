package com.jinnylee.contacts.data.repository

import com.jinnylee.contacts.data.database.ContactDao
import com.jinnylee.contacts.data.database.ContactEntity
import com.jinnylee.contacts.data.datasource.ContactsDataSource
import com.jinnylee.contacts.domain.model.Contact
import com.jinnylee.contacts.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainRepositoryImpl(
    private val contactsDataSource: ContactsDataSource,
    private val contactDao: ContactDao
) : MainRepository {

    override suspend fun getContacts(): List<Contact> {
        return contactsDataSource.getContacts()
    }

    override suspend fun toggleFavorite(contact: Contact) {
        contactDao.insert(
            ContactEntity(
                contactId = contact.id,
                name = contact.name,
                phoneNumber = contact.phoneNumber
            )
        )
    }

    override suspend fun removeFavorite(contact: Contact) {
        contactDao.delete(contact.id)
    }

    override fun getFavoriteContacts(): Flow<List<Contact>> {
        return contactDao.getAll().map { entities ->
            entities.map {
                Contact(
                    id = it.contactId,
                    name = it.name,
                    phoneNumber = it.phoneNumber,
                    initial = it.name.first().toString()
                )
            }
        }
    }
}
