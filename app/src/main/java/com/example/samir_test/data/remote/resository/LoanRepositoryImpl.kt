package com.example.samir_test.data.remote.resository

import com.example.samir_test.data.BaseResult
import com.example.samir_test.data.remote.ApiService
import com.example.samir_test.data.remote.response.toDomain
import com.example.samir_test.domain.model.LoanData
import com.example.samir_test.domain.repository.LoanRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class LoanRepositoryImpl(
    private val apiService: ApiService
) : LoanRepository {
    override suspend fun getLoan(): Flow<BaseResult<List<LoanData>>> {
        return flow {
            try {
                val response = apiService.getLoanData().body()!!
                emit(BaseResult.Success(response.map { it.toDomain() }))
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        emit(BaseResult.Error(t.message() ?: "Ups, something went wrong!"))
                    }
                    is UnknownHostException -> {
                        emit(BaseResult.Error(t.message ?: "Check your internet connection"))
                    }
                    is SocketTimeoutException -> {
                        emit(BaseResult.Error(t.message ?: "Timeout"))
                    }
                    else -> emit(BaseResult.Error(t.message ?: "Something went wrong"))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

}