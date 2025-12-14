package com.example.effectivemobile.presentation.mainScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.effectivemobile.R
import com.example.effectivemobile.data.tables.СoursesDb
import com.example.effectivemobile.databinding.FragmentMainScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainScreenFragment : Fragment() {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainScreenViewModel by viewModels()

    private val recyclerViewAdapter =
        RecyclerViewAdapter { course, isLiked -> onCheckBoxClick(course, isLiked) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerview.adapter = recyclerViewAdapter

        binding.button.setOnClickListener {
            viewModel.toggleSort()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.items.collect { courses ->
                    recyclerViewAdapter.setData(
                        courses,
                        viewModel.checkBoxState()
                    )
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isSortedDescending.collect { isSorted ->
                    binding.textView.text =
                        if (isSorted) getString(R.string.by_date_added)
                        else getString(R.string.second_filtr)
                }
            }
        }
    }

    private fun onCheckBoxClick(course: СoursesDb, isLiked: Boolean) {
        viewModel.updateLike(course, isLiked)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}