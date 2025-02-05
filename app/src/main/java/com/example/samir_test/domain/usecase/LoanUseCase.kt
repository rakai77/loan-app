package com.example.samir_test.domain.usecase

import com.example.samir_test.data.BaseResult
import com.example.samir_test.domain.model.LoanData
import com.example.samir_test.domain.repository.LoanRepository
import kotlinx.coroutines.flow.Flow

class LoanUseCase(
    private val repository: LoanRepository
) {

    suspend fun invoke() : Flow<BaseResult<List<LoanData>>> {
        return repository.getLoan()
    }
}