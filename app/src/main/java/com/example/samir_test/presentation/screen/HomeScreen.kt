package com.example.samir_test.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.samir_test.domain.model.LoanData
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    val viewModel: HomeViewModel = koinViewModel()
    val loanUiState by viewModel.loanUiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getLoanData()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(text = "Loan List")
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when(loanUiState) {
                is LoanUiState.Loading -> {
                    val isLoading = (loanUiState as LoanUiState.Loading).isLoading
                    if (isLoading) {
                        item {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
                is LoanUiState.SuccessGetLoan -> {
                    val loanList = (loanUiState as LoanUiState.SuccessGetLoan).item
                    items(loanList.size) { index ->
                        LoanItemCard(loan = loanList[index])
                    }

                }
                is LoanUiState.Error -> {
                    val errorMessage = (loanUiState as LoanUiState.Error).message
                    item {
                        Text(text = errorMessage)
                    }
                }
                else -> Unit
            }
        }
    }
}

@Composable
fun LoanItemCard(loan: LoanData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = loan.borrower.name,
                fontSize = 18.sp,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Loan Amount: $${loan.amount}", fontSize = 16.sp)
            Text(text = "Interest Rate: ${loan.interestRate}%", fontSize = 16.sp)
            Text(text = "Term: ${loan.term} months", fontSize = 16.sp)
            Text(text = "Purpose: ${loan.purpose}", fontSize = 16.sp)
            Text(
                text = "Risk Rating: ${loan.riskRating}",
                fontSize = 16.sp,
                color = getRiskColor(loan.riskRating)
            )
        }
    }
}

@Composable
fun getRiskColor(risk: String): Color {
    return when (risk) {
        "A" -> Color.Green
        "B" -> Color.Blue
        "C" -> Color.Red
        else -> Color.Gray
    }
}
