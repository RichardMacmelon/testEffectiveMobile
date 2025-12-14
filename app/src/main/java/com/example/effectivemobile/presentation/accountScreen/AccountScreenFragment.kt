package com.example.effectivemobile.presentation.accountScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.FragmentAccountScreenBinding
import com.example.effectivemobile.databinding.FragmentFavoritesScreenBinding

class AccountScreenFragment : Fragment() {

    private var _binding: FragmentAccountScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccountScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}