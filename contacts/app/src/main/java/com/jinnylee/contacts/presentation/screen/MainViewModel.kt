package com.jinnylee.contacts.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jinnylee.contacts.domain.model.Contact
import com.jinnylee.contacts.domain.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    init {
        loadContacts()
        observeFavorites()
    }

    private fun loadContacts() {
        viewModelScope.launch {
            _state.update {
                it.copy(contacts = repository.getContacts())
            }
        }
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            repository.getFavoriteContacts().collect { favorites ->
                _state.update {
                    it.copy(favoriteContacts = favorites.map { contact -> contact.id }.toSet())
                }
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _state.update {
            it.copy(searchQuery = query)
        }
    }

    fun toggleFavorite(contact: Contact) {
        val isFavorite = _state.value.favoriteContacts.contains(contact.id)
        viewModelScope.launch {
            if (isFavorite) {
                repository.removeFavorite(contact)
            } else {
                repository.toggleFavorite(contact)
            }
        }
    }
}
