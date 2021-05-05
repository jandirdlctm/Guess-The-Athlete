package com.example.guesstheathlete

import androidx.lifecycle.ViewModel

private const val TAG = "FactsViewModel"
class FactsViewModel: ViewModel() {
    var currentIndex = 0
    private val factsBank = listOf(
        Facts(R.string.facts_neymar, "neymar"),
        Facts(R.string.facts_lebron, "lebron"),
        Facts(R.string.facts_messi, "messi"),
        Facts(R.string.facts_kobe, "kobe")
    )
    val currentQuestionAnswer: String
        get() = factsBank[currentIndex].answer

    val currentFactText: Int
        get() = factsBank[currentIndex].textResId

    fun moveToNextFact() {
        currentIndex = (currentIndex + 1) % factsBank.size

    }
    val factsBankSize: Int
        get() = factsBank.size
}