package com.iago.android.clist.customers.model

import com.google.gson.annotations.SerializedName

data class Customer (
    @SerializedName("UserName") val userName: String,
    @SerializedName("FirstName") val firstName: String,
    @SerializedName("MiddleName") val middleName: String,
    @SerializedName("LastName") val lastName: String,
    @SerializedName("Gender") val gender: String,
    @SerializedName("Emails") val emails: List<String>,
    @SerializedName("AddressInfo") val addressInfo: List<AddressInfo>
)

data class AddressInfo(
    @SerializedName("Address") val address: String,
    @SerializedName("City") val city: City
)

data class City(
    @SerializedName("Name") val name: String,
    @SerializedName("CountryRegion") val country: String,
    @SerializedName("ID") val region: String
)