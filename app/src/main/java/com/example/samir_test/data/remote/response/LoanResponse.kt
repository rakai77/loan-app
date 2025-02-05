package com.example.samir_test.data.remote.response

import com.example.samir_test.domain.model.BorrowerData
import com.example.samir_test.domain.model.CollateralData
import com.example.samir_test.domain.model.DocumentsData
import com.example.samir_test.domain.model.InstallmentsData
import com.example.samir_test.domain.model.LoadData
import com.example.samir_test.domain.model.RepaymentScheduleData
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

data class BorrowerResponse(

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
	val borrower: BorrowerResponse? = null,

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

fun InstallmentsResponse.toDomain() = InstallmentsData(
	amountDue = amountDue ?: 0,
	dueDate = dueDate ?: ""
)

fun RepaymentScheduleResponse.toDomain() = RepaymentScheduleData(
	installments = installments.map { it.toDomain() }
)

fun BorrowerResponse.toDomain() = BorrowerData(
	creditScore = creditScore ?: 0,
	name = name ?: "",
	id = id ?: "",
	email = email ?: ""
)

fun DocumentsResponse.toDomain() = DocumentsData(
	type = type ?: "",
	url = url ?: ""
)

fun CollateralResponse.toDomain() = CollateralData(
	type = type ?: "",
	value = value ?: 0
)

fun LoanResponse.toDomain() = LoadData(
	interestRate = interestRate ?: 0,
	amount = amount ?: 0,
	purpose = purpose ?: "",
	documents = documents.map { it.toDomain() },
	borrower = borrower?.toDomain() ?: BorrowerData(0, "", "", ""),
	term = term ?: 0,
	id = id ?: "",
	collateral = collateral?.toDomain() ?: CollateralData("",0),
	repaymentSchedule = repaymentSchedule?.toDomain() ?: RepaymentScheduleData(emptyList()),
	riskRating = riskRating ?: "",
)