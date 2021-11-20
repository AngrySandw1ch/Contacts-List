package com.example.contactslist.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.contactslist.databinding.FragmentDetailsBinding
import com.example.contactslist.utils.KeyboardUtils
import com.example.contactslist.viewmodel.ContactViewModel

class DetailsFragment : Fragment() {
    lateinit var binding: FragmentDetailsBinding
    lateinit var viewModel: ContactViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(ContactViewModel::class.java)

        binding.editedContactName.setText(arguments?.getString("name_text"))
        binding.editedContactPhoneNumber.setText(arguments?.getString("phoneNumber_text"))


        binding.applyChangesButton.setOnClickListener {
            viewModel.changeContactDetails(
                binding.editedContactName.text.toString(),
                binding.editedContactPhoneNumber.text.toString()
            )
            viewModel.save()
            viewModel.loadContacts()
            KeyboardUtils.hideKeyboard(it)
            findNavController().navigateUp()
        }
        binding.denyChangesButton.setOnClickListener {
            KeyboardUtils.hideKeyboard(it)
            findNavController().navigateUp()
        }

        return binding.root
    }

}