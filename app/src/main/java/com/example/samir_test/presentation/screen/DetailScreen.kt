package com.example.samir_test.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.samir_test.domain.model.LoanData


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    onNavigateBack: () -> Unit,
    loanDetail: LoanData
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Loan Details") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBackIosNew, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            item {
                LoanDetailCard(loanDetail)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Repayment Schedule", fontSize = 18.sp, style = MaterialTheme.typography.titleMedium)
            }
            items(loanDetail.repaymentSchedule.installments) {
                RepaymentScheduleCard(loanDetail)
            }
        }
    }
}

@Composable
fun LoanDetailCard(loanDetail: LoanData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Borrower: ${loanDetail.borrower.name}", fontSize = 18.sp)
            Text(text = "Email: ${loanDetail.borrower.email}", fontSize = 16.sp)
            Text(text = "Credit Score: ${loanDetail.borrower.creditScore}", fontSize = 16.sp)
            Text(text = "Collateral Type: ${loanDetail.collateral.type}", fontSize = 16.sp)
            Text(text = "Collateral Value: $${loanDetail.collateral.value}", fontSize = 16.sp)
        }
    }
}

// Composable function for Repayment Schedule Card
@Composable
fun RepaymentScheduleCard(scheduleItem: LoanData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        repeat(scheduleItem.repaymentSchedule.installments.size) { index ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Due Date: ${scheduleItem.repaymentSchedule.installments[index].dueDate}", fontSize = 16.sp)
                Text(text = "Amount Due: $${scheduleItem.repaymentSchedule.installments[index].amountDue}", fontSize = 16.sp)
            }
        }

    }
}