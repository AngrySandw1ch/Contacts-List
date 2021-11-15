package com.example.contactslist.db

import android.os.Build
import android.provider.ContactsContract
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.contactslist.AppActivity
import com.example.contactslist.dto.Contact

class ContactsDB {
    //private val mAppActivity = AppActivity()
    private var contactsList = listOf(
        Contact(
            id = 1,
            name ="Vasya Pupkin",
            phoneNumber = "89991690573"
        ),
        Contact(
            id = 2,
            name ="Petya Petrov",
            phoneNumber = "89991234567"
        ),
        Contact(
            id = 3,
            name = "Ivan Ivanov",
            phoneNumber = "8172909376"
        ),
        Contact(
            id = 4,
            name = "Vasya Vasin",
            phoneNumber = "89293345522"
        ),
        Contact(
            id = 5,
            name = "Ivan Vasilevich",
            phoneNumber = "89293560928"
        ),
        Contact(
            id = 6,
            name = "Ilya lisanin",
            phoneNumber = "89275540798"
        )
    )
    private val data = MutableLiveData(contactsList)
   /* private val cursor = mAppActivity.contentResolver.query(
        ContactsContract.Contacts.CONTENT_URI,
        null,
        null,
        null,
        null
    )*/



    fun getById(id: Long) : Contact {
        val contact = data.value?.first {
            it.id == id
        }
        return contact!!
    }
    fun removeById(id: Long) {
        contactsList = contactsList.filter {
            it.id != id
        }
        data.value = contactsList
    }
    fun getData(): MutableLiveData<List<Contact>> = data

    fun save(contact: Contact) {
        contactsList = contactsList.map {
            if (it.id != contact.id) it else it.copy(name = contact.name, phoneNumber = contact.phoneNumber)
        }
    }
}