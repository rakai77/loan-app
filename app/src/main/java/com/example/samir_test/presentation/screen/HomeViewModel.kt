package com.example.samir_test.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samir_test.data.BaseResult
import com.example.samir_test.domain.usecase.LoanUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val loanUseCase: LoanUseCase
) : ViewModel() {

    private val _loanUiState = MutableStateFlow<LoanUiState>(LoanUiState.Idle)
    val loanUiState = _loanUiState.asStateFlow()

    fun getLoanData() {
        viewModelScope.launch {
            _loanUiState.update {
                LoanUiState.Loading(isLoading = true)
            }
            loanUseCase.invoke().collect { result ->
                _loanUiState.update {
                    LoanUiState.Loading(isLoading = false)
                }
                when(result) {
                    is BaseResult.Success -> {
                        _loanUiState.update {
                            LoanUiState.SuccessGetLoan(item = result.data)
                        }
                    }
                    is BaseResult.Error -> {
                        _loanUiState.update {
                            LoanUiState.Error(message = result.message)
                        }
                    }
                }
            }
        }
    }
}