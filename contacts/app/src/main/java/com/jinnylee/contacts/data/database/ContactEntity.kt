package com.jinnylee.contacts.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_contacts")
data class ContactEntity(
    @PrimaryKey val contactId: Long,
    val name: String,
    val phoneNumber: String
)