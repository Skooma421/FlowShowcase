package com.example.flowshowcase

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flowshowcase.databinding.FragmentListBinding
import kotlinx.coroutines.launch

class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private val viewModel: TaskViewModel by viewModels()
    private lateinit var adapter: TaskAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TaskAdapter { taskId ->
            viewModel.removeTask(taskId)
        }

        binding.recyclerView.apply{
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ListFragment.adapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.tasks.collect { taskList ->
                    adapter.submitList(taskList)
                }

            }
        }


    }
}