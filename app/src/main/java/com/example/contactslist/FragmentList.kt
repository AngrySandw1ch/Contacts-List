package com.example.contactslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.contactslist.adapter.ContactAdapter
import com.example.contactslist.adapter.OnActionListener
import com.example.contactslist.databinding.FragmentListBinding
import com.example.contactslist.dto.Contact
import com.example.contactslist.viewmodel.ContactViewModel


class FragmentList : Fragment() {
    private lateinit var binding: FragmentListBinding
    val viewModel: ContactViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        val adapter = ContactAdapter(object : OnActionListener {
            override fun edit(contact: Contact) {
                viewModel.edit(contact)
                Bundle().apply {
                    putString("name_text", contact.name)
                    putString("phoneNumber_text", contact.phoneNumber)
                    findNavController().navigate(R.id.action_listFragment_to_detailsFragment, this)
                }
            }

            override fun remove(contact: Contact) {
                viewModel.removeById(contact.id)
            }
        })
        binding.container.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.edited.observe(viewLifecycleOwner) {
            if (it.id == 0L) {
                return@observe
            }
        }

        return binding.root
    }

}