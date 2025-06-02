package com.o9tech.prankcall.Screen.Chat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel  @Inject constructor() : ViewModel() {
    var messages by mutableStateOf(listOf<ChatMessage>())
        private set

    fun sendMessage(message: String) {
        if (message.isNotBlank()) {
            messages = messages + ChatMessage(message, isSentByMe = true)

            // Simulate reply after sending
//            messages = messages + ChatMessage("Reply to: $message", isSentByMe = false)
            viewModelScope.launch {
                delay(1000L)
                messages = messages + ChatMessage("Reply to: $message", isSentByMe = false)
            }
        }
    }
}


