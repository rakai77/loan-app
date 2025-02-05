package com.example.samir_test.domain.model


data class RepaymentScheduleData(
    val installments: List<InstallmentsData>
)

data class CollateralData(
    val type: String,
    val value: Int
)

data class DocumentsData(
    val type: String,
    val url: String
)

data class BorrowerData(
    val creditScore: Int,
    val name: String,
    val id: String,
    val email: String
)

data class LoanData(
    val interestRate: Any,
    val amount: Int,
    val purpose: String,
    val documents: List<DocumentsData>,
    val borrower: BorrowerData,
    val term: Int,
    val id: String,
    val collateral: CollateralData,
    val repaymentSchedule: RepaymentScheduleData,
    val riskRating: String
)

data class InstallmentsData(
    val amountDue: Int,
    val dueDate: String
)