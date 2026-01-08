package com.jinnylee.contacts.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jinnylee.contacts.domain.model.Contact
import com.jinnylee.contacts.domain.usecase.GetContactsUseCase
import com.jinnylee.contacts.domain.usecase.GetFavoriteContactsUseCase
import com.jinnylee.contacts.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getContactsUseCase: GetContactsUseCase,
    private val getFavoriteContactsUseCase: GetFavoriteContactsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
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
                it.copy(contacts = getContactsUseCase())
            }
        }
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            getFavoriteContactsUseCase().collect { favorites ->
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
            toggleFavoriteUseCase(contact, isFavorite)
        }
    }
}
