package com.iago.android.customerlistapp.customers.service

import com.iago.android.customerlistapp.customers.model.Customer
import retrofit2.Call
import retrofit2.http.GET

interface CustomerService {

    @GET("customers.json")
    fun customersList(): Call<List<Customer>>

}