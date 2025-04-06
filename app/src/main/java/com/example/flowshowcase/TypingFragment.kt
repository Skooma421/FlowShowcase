package com.example.flowshowcase

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flowshowcase.databinding.FragmentTypingBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TypingFragment : BaseFragment<FragmentTypingBinding>(FragmentTypingBinding::inflate) {

    private val viewModel: TypingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.typingField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.userTyping()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.counterFragmentButton.setOnClickListener {
            findNavController().navigate(R.id.action_typingFragment_to_counterFragment)
        }

        binding.listFragmentButton.setOnClickListener {
            findNavController().navigate(R.id.action_typingFragment_to_listFragment)
        }

        lifecycleScope.launch {
            viewModel.isTyping.collectLatest { isTyping ->
                binding.typingIndicator.visibility = if (isTyping) View.VISIBLE else View.GONE
            }
        }
    }
}
