package com.example.samir_test.presentation.screen

import com.example.samir_test.domain.model.LoanData

sealed class LoanUiState {
    data class SuccessGetLoan(val item: List<LoanData>) : LoanUiState()
    data class Loading(val isLoading: Boolean) : LoanUiState()
    data class Error(val message: String) : LoanUiState()
    data object Idle : LoanUiState()
}