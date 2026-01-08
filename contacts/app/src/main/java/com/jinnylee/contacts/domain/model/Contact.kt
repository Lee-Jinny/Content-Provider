package com.jinnylee.contacts.domain.model

data class Contact(
    val id: Long,
    val name: String,
    val phoneNumber: String,
    val initial: String   // 초성
)
