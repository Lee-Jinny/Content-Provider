package com.jinnylee.contacts.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jinnylee.contacts.domain.model.Contact
import com.jinnylee.contacts.presentation.component.ContactListItem
import com.jinnylee.contacts.presentation.component.ContactSearchBar

@Composable
fun MainScreen(
    state: MainState,
    onAction: (MainAction) -> Unit
) {
    val filteredContacts = remember(state.contacts, state.searchQuery) {
        if (state.searchQuery.isBlank()) {
            state.contacts
        } else {
            state.contacts.filter { contact ->
                contact.name.contains(state.searchQuery, ignoreCase = true) ||
                contact.phoneNumber.contains(state.searchQuery) ||
                contact.initial.contains(state.searchQuery, ignoreCase = true)
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ContactSearchBar(
                query = state.searchQuery,
                onQueryChange = { onAction(MainAction.UpdateSearchQuery(it)) },
                onSearch = { onAction(MainAction.Search(it)) },
                modifier = Modifier.background(MaterialTheme.colorScheme.surface)
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(filteredContacts, key = { it.id }) { contact ->
                ContactListItem(
                    contact = contact,
                    isFavorite = state.favoriteContacts.contains(contact.id),
                    onFavoriteClick = { onAction(MainAction.ToggleFavorite(contact)) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        state = MainState(
            contacts = listOf(
                Contact(1, "홍길동", "010-1111-2222", "ㅎ"),
                Contact(2, "가나다", "010-3333-4444", "ㅇ")
            ),
            favoriteContacts = setOf(1),
            searchQuery = ""
        ),
        onAction = {}
    )
}