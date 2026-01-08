package com.jinnylee.contacts.data.datasource

import android.content.ContentResolver
import android.provider.ContactsContract
import com.jinnylee.contacts.domain.model.Contact
import com.jinnylee.contacts.util.KoreanUtils

class ContactsDataSource(
    private val contentResolver: ContentResolver
) {

    fun getContacts(): List<Contact> {
        val result = mutableListOf<Contact>()

        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )

        cursor?.use {
            val idIdx = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
            val nameIdx = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val numberIdx = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)

            while (it.moveToNext()) {
                val name = it.getString(nameIdx) ?: ""
                result.add(
                    Contact(
                        id = it.getLong(idIdx),
                        name = name,
                        phoneNumber = it.getString(numberIdx) ?: "",
                        initial = KoreanUtils.getInitialSound(name)
                    )
                )
            }
        }
        return result
    }
}
