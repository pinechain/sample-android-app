package com.pinechain.sampleapplication.core.model

sealed class ScreenState {
    data object Idle: ScreenState()
    data object Loading: ScreenState()
    data class Finished(val data: String? = null, val isError: Boolean = false): ScreenState()
}