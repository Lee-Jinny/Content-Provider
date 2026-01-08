package com.jinnylee.contacts.presentation.screen

import com.jinnylee.contacts.domain.model.Contact

data class MainState(
    val contacts: List<Contact> = emptyList(),
    val favoriteContacts: Set<Long> = emptySet(),
    val searchQuery: String = ""
)
