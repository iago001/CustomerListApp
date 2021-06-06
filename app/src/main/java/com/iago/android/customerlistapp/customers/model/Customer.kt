package com.iago.android.customerlistapp.customers.model

import com.google.gson.annotations.SerializedName

data class Customer(
    @SerializedName("UserName") val userName: String,
    @SerializedName("FirstName") val firstName: String,
    @SerializedName("MiddleName") val middleName: String,
    @SerializedName("LastName") val lastName: String
)