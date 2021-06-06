package com.iago.android.customerlistapp.customers.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iago.android.customerlistapp.customers.model.Customer
import com.iago.android.customerlistapp.customers.service.CustomerService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://androidbasics-e48d1.firebaseapp.com/"

class NetworkRepository {

    private val customerService: CustomerService
    private val customerList = MutableLiveData<List<Customer>>()

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        customerService = retrofit.create(CustomerService::class.java)
    }

    fun customerList(): LiveData<List<Customer>> {
        return customerList
    }

    fun fetchCustomerList() {
        customerService.customersList().enqueue(object: Callback<List<Customer>> {
            override fun onFailure(call: Call<List<Customer>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<List<Customer>>,
                response: Response<List<Customer>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        customerList.value = response.body()
                    }
                } else {
                    Log.e("NetworkRepo", "response unsuccessful")
                }
            }

        })
    }
}