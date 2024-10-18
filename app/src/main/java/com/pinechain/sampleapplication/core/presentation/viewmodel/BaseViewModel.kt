package com.pinechain.sampleapplication.core.presentation.viewmodel

import androidx.lifecycle.ViewModel

// Here we add an abstraction layer so that we can write common functionality for all viewmodels in the app.
// This is particularly useful for maintaining the app design and ensuring consistency across different screens,
// especially in projects designed to last for a long time.

// As a bonus, we can totally split the core module into a separate one and have it deal with integration pains
// regarding the native API, completely isolating the other layers from this burden.
open class BaseViewModel : ViewModel() {
}