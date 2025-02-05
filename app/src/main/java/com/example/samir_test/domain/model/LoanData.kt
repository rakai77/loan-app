package com.example.samir_test.domain.model

import com.google.gson.annotations.SerializedName


data class RepaymentScheduleData(

    @field:SerializedName("installments")
    val installments: List<InstallmentsData>
)

data class CollateralData(

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("value")
    val value: Int? = null
)

data class DocumentsData(

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("url")
    val url: String? = null
)

data class Borrower(

    @field:SerializedName("creditScore")
    val creditScore: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("email")
    val email: String? = null
)

data class LoadData(

    @field:SerializedName("interestRate")
    val interestRate: Any? = null,

    @field:SerializedName("amount")
    val amount: Int? = null,

    @field:SerializedName("purpose")
    val purpose: String? = null,

    @field:SerializedName("documents")
    val documents: List<DocumentsData>,

    @field:SerializedName("borrower")
    val borrower: Borrower? = null,

    @field:SerializedName("term")
    val term: Int? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("collateral")
    val collateral: CollateralData? = null,

    @field:SerializedName("repaymentSchedule")
    val repaymentSchedule: RepaymentScheduleData? = null,

    @field:SerializedName("riskRating")
    val riskRating: String? = null
)

data class InstallmentsData(

    @field:SerializedName("amountDue")
    val amountDue: Int? = null,

    @field:SerializedName("dueDate")
    val dueDate: String? = null
)
