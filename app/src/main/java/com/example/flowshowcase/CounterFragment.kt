package com.example.flowshowcase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.flowshowcase.databinding.FragmentCounterBinding
import kotlinx.coroutines.launch

class CounterFragment : BaseFragment<FragmentCounterBinding>(FragmentCounterBinding::inflate) {
    private val viewModel: CountViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.count.collect {count ->
                    binding.countAmount.text = count.toString()
                }
            }
        }

        binding.countButton.setOnClickListener{
            viewModel.addCount()
        }
    }
}