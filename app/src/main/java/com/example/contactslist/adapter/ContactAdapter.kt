package com.example.contactslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.contactslist.R
import com.example.contactslist.databinding.CardContactBinding
import com.example.contactslist.dto.Contact

interface OnActionListener {
    fun edit(contact: Contact)
    fun remove(contact: Contact)
}

class ContactAdapter(
    private val onActionListener: OnActionListener
) : ListAdapter<Contact, ContactViewHolder>(ContactDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = CardContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding, onActionListener)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = getItem(position)
        holder.bind(contact)
    }
}

class ContactViewHolder(
    private val binding: CardContactBinding,
    private val onActionListener: OnActionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(contact: Contact) {
        with(binding) {
            contactName.text = contact.name
            contactPhoneNumber.text = contact.phoneNumber

            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    setOnMenuItemClickListener { item ->
                        when(item.itemId) {
                            R.id.edit -> {
                                onActionListener.edit(contact)
                                true
                            }
                            R.id.remove -> {
                                onActionListener.remove(contact)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }
        }
    }
}

class ContactDiffCallBack: DiffUtil.ItemCallback<Contact> () {
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem == newItem
    }

}
