package com.example.effectivemobile.presentation.favoritesScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.effectivemobile.R
import com.example.effectivemobile.data.tables.СoursesDb
import com.example.effectivemobile.databinding.FragmentFavoritesScreenBinding
import com.example.effectivemobile.databinding.FragmentMainScreenBinding
import com.example.effectivemobile.presentation.extensions.toReadableDate
import com.example.effectivemobile.presentation.favoritesScreen.FavoritesScreenViewModel
import com.example.effectivemobile.presentation.mainScreen.MainScreenViewModel
import com.example.effectivemobile.presentation.mainScreen.RecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue


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