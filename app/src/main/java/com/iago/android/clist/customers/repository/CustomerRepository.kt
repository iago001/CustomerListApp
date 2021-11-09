package com.iago.android.clist.customers.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iago.android.clist.customers.model.Customer
import com.iago.android.clist.customers.service.CustomerService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class CustomerRepository @Inject constructor(var customerService: CustomerService) {

    private val customerList: MutableLiveData<List<Customer>>

    init {
        customerList = MutableLiveData()
    }

    fun customerListLiveData(): LiveData<List<Customer>> {
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
                    response.body().let {
                        customerList.value = response.body()
                    }
                } else {
                    Log.d(CustomerRepository::class.simpleName, "Response unsuccessful")
                }
            }

        })
    }
}