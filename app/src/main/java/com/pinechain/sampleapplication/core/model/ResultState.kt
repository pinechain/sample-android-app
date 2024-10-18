package com.pinechain.sampleapplication.core.model

// This is actually very optional. I just like to create a "communication object" between domain and
// presentation in order to keep the design different from the communication between domain and data.
// I believe that, depending on the project, it might provide us with a small decoupling benefit
// should the contracts in the data layer change, turning domain into some kind of translator between
// data and presentation, as well as treating their own business rules.
sealed class ResultState(val resultData: String?) {
    class Success(data: String? = null) : ResultState(data)
    class Failure(data: String? = null) : ResultState(data)
}
