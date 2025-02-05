package com.example.samir_test.domain.repository

import com.example.samir_test.data.BaseResult
import com.example.samir_test.domain.model.LoanData
import kotlinx.coroutines.flow.Flow

interface LoanRepository {

    suspend fun getLoan() : Flow<BaseResult<List<LoanData>>>
}