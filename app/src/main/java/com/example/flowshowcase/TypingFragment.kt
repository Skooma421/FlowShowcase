package com.example.flowshowcase

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.flowshowcase.databinding.FragmentTypingBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TypingFragment : BaseFragment<FragmentTypingBinding>(FragmentTypingBinding::inflate) {

    private val viewModel: TypingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTypingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.userTyping()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        lifecycleScope.launch {
            viewModel.isTyping.collectLatest { isTyping ->
                binding.typingIndicator.visibility = if (isTyping) View.VISIBLE else View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}