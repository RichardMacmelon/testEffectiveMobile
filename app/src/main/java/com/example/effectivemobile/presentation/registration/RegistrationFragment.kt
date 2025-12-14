package com.example.effectivemobile.presentation.registration

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.FragmentRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val fullText = binding.registrTextView.text.toString()

        binding.registrTextView.text =
            viewModel.getColored(fullText)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.formState.collect { state ->
                    binding.entranceButton.isEnabled = state.isButtonEnabled
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.openLinkEvent.collect { url ->
                    openUrl(url)
                }
            }
        }

        binding.emailEditText.addTextChangedListener { text ->
            viewModel.onEmailChanged(text.toString())
        }

        binding.passwordEditText.addTextChangedListener { text ->
            viewModel.onPasswordChanged(text.toString())
        }

        binding.entranceButton.setOnClickListener {
            viewModel.onLoginClicked {
                findNavController().navigate(R.id.action_registrationFragment_to_mainNavigation)
            }
        }

        binding.vkImageButton.setOnClickListener {
            viewModel.onVkClicked()
        }

        binding.odnImageButton.setOnClickListener {
            viewModel.onOkClicked()
        }

    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}