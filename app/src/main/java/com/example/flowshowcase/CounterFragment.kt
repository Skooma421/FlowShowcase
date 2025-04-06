package com.example.flowshowcase

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.flowshowcase.databinding.FragmentCounterBinding
import kotlinx.coroutines.launch

class CounterFragment : BaseFragment<FragmentCounterBinding>(FragmentCounterBinding::inflate) {
    private val viewModel: CountViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let { binding ->
            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.count.collect { count ->
                        binding.countAmount.text = count.toString()
                    }
                }
            }

            binding.typingFragmentButton.setOnClickListener {
                findNavController().navigate(R.id.action_counterFragment_to_typingFragment)
            }

            binding.listFragmentButton.setOnClickListener {
                findNavController().navigate(R.id.action_counterFragment_to_listFragment)
            }

            binding.countButton.setOnClickListener {
                viewModel.addCount()
            }
        }
    }
}