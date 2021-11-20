package com.example.contactslist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.contactslist.R
import com.example.contactslist.databinding.FragmentContactBinding
import com.example.contactslist.viewmodel.ContactViewModel

class ContactFragment : Fragment() {
    lateinit var binding: FragmentContactBinding
    private val viewModel = ContactViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactBinding.inflate(inflater, container, false)
        val id = arguments?.getLong("contact_id")
        val contact = id?.let { viewModel.geById(it) }
        binding.contact.contactName.text = contact?.name
        binding.contact.contactPhoneNumber.text = contact?.phoneNumber
        Glide.with(binding.contact.avatar)
            .load(contact?.imageUrl)
            .placeholder(R.drawable.ic_face_24)
            .error(R.drawable.ic_baseline_error_24)
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .timeout(30_000)
            .into(binding.contact.avatar)

        return binding.root
    }

}