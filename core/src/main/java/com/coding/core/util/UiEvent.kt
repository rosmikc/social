package com.coding.core.util

sealed class UiEvent {
    data class ShowAlertDialogue(val message: String): UiEvent()
}
