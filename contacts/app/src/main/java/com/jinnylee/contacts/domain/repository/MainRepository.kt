package com.jinnylee.contacts.domain.repository

import com.jinnylee.contacts.domain.model.Contact
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getContacts(): List<Contact>

    suspend fun toggleFavorite(contact: Contact)

    suspend fun removeFavorite(contact: Contact)

    fun getFavoriteContacts(): Flow<List<Contact>>
}