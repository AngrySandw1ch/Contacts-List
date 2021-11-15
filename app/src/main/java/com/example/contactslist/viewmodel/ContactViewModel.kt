package com.example.contactslist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactslist.db.ContactsDB
import com.example.contactslist.dto.Contact

private val emptyContact = Contact(
    id = 0L,
    name = "",
    phoneNumber = ""
)

class ContactViewModel: ViewModel() {
    private val contactsDB = ContactsDB()
    val data = contactsDB.getData()
    val edited = MutableLiveData(emptyContact)

    fun loadContacts() {
        val storage = contactsDB.getData()
        if (storage.value.isNullOrEmpty()) {
            return
        }
        data.value = storage.value
    }

    fun geById(id: Long) = contactsDB.getById(id)

    fun removeById(id: Long) = contactsDB.removeById(id)

    fun edit(contact: Contact) {
        edited.value = contact
    }

    fun changeContactDetails(name: String, phoneNumber: String) {
        val nameText = name.trim()
        val phoneNumberText = phoneNumber.trim()
        if (edited.value?.name == nameText && edited.value?.phoneNumber == phoneNumberText) {
            return
        }
        edited.value = edited.value?.copy(name = nameText, phoneNumber = phoneNumberText)
    }

    fun save() {
        edited.value?.let { contactsDB.save(it) }
        edited.value = emptyContact
    }

}