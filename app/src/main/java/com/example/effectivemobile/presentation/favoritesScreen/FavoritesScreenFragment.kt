package com.example.effectivemobile.presentation.favoritesScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.effectivemobile.databinding.FragmentFavoritesScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FavoritesScreenFragment : Fragment() {

    private var _binding: FragmentFavoritesScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoritesScreenViewModel by viewModels()

    private val recyclerViewAdapter = FavoritesScreenAdapter { id -> onCheckBoxClick(id) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.recyclerView.adapter = recyclerViewAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.allLikeCourses.collect { courses ->
                    recyclerViewAdapter.setData(courses.reversed())
                }
            }
        }

    }

    private fun onCheckBoxClick(id: Int) {
        viewModel.deleteCourse(id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}