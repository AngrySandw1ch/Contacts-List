package com.example.contactslist.dto

data class Contact(
    val id: Long,
    val name: String,
    val phoneNumber: String,
    val imageUrl: String = "https://picsum.photos/48"
    )
