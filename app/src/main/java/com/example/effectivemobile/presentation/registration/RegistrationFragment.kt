package com.example.effectivemobile.presentation.registration

import com.example.effectivemobile.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.effectivemobile.databinding.FragmentRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.entranceButton.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_mainNavigation)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}