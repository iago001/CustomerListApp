package com.iago.android.customerlistapp.customers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.iago.android.customerlistapp.customers.model.Customer
import com.iago.android.customerlistapp.customers.repository.NetworkRepository

class CustomerViewModel: ViewModel() {

    private val repository = NetworkRepository()

    fun loadCustomerList(): LiveData<List<Customer>> {
        repository.fetchCustomerList()
        return repository.customerList()
    }
}