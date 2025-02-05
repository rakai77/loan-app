package com.example.samir_test.di

import com.example.samir_test.data.remote.ApiService
import com.example.samir_test.data.remote.resository.LoanRepositoryImpl
import com.example.samir_test.domain.repository.LoanRepository
import com.example.samir_test.domain.usecase.LoanUseCase
import com.example.samir_test.presentation.screen.HomeViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/andreascandle/p2p_json_test/main/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single { get<Retrofit>().create(ApiService::class.java) }

    single<LoanRepository> { LoanRepositoryImpl(get()) }

    single { LoanUseCase(get()) }

    viewModel { HomeViewModel(get()) }

}