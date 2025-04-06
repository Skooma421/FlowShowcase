package com.example.flowshowcase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class TypingViewModel : ViewModel() {
    private val _isTyping = MutableSharedFlow<Boolean>(replay = 1)
    val isTyping = _isTyping.asSharedFlow()

    private var typingJob: Job? = null

    fun userTyping() {
        _isTyping.tryEmit(true)

        typingJob?.cancel()
        typingJob = viewModelScope.launch {
            delay(2000)
            _isTyping.tryEmit(false)
        }
    }
}