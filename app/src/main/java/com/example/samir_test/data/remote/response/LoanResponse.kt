package com.example.samir_test.data.remote.response

import com.google.gson.annotations.SerializedName


data class RepaymentScheduleResponse(

	@field:SerializedName("installments")
	val installments: List<InstallmentsResponse>
)

data class CollateralResponse(

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("value")
	val value: Int? = null
)

data class DocumentsResponse(

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

data class LoanResponse(

	@field:SerializedName("interestRate")
	val interestRate: Any? = null,

	@field:SerializedName("amount")
	val amount: Int? = null,

	@field:SerializedName("purpose")
	val purpose: String? = null,

	@field:SerializedName("documents")
	val documents: List<DocumentsResponse>,

	@field:SerializedName("borrower")
	val borrower: Borrower? = null,

	@field:SerializedName("term")
	val term: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("collateral")
	val collateral: CollateralResponse? = null,

	@field:SerializedName("repaymentSchedule")
	val repaymentSchedule: RepaymentScheduleResponse? = null,

	@field:SerializedName("riskRating")
	val riskRating: String? = null
)

data class InstallmentsResponse(

	@field:SerializedName("amountDue")
	val amountDue: Int? = null,

	@field:SerializedName("dueDate")
	val dueDate: String? = null
)
