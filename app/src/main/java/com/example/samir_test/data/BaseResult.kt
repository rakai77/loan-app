package com.example.samir_test.data

sealed class BaseResult<out T> {
    data class Success<T>(val data: T) : BaseResult<T>()
    data class Error(val message: String) : BaseResult<Nothing>()
}