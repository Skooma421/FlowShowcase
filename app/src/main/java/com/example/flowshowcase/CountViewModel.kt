package com.example.flowshowcase

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CountViewModel : ViewModel() {

    private val _count = MutableStateFlow<Int>(0)
    val count: StateFlow<Int> = _count


    fun addCount() {
        _count.value += 1
    }

}