package com.example.effectivemobile.presentation.favoritesScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.FragmentFavoritesScreenBinding
import com.example.effectivemobile.databinding.FragmentMainScreenBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoritesScreenFragment : Fragment() {

    private var _binding: FragmentFavoritesScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}