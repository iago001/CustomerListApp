package com.iago.android.clist.customers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iago.android.clist.customers.model.Customer
import com.iago.android.clist.customers.repository.CustomerRepository

class CustomerViewModel : ViewModel() {

    private val repository = CustomerRepository()
    private val _selectedCustomer: MutableLiveData<Customer> = MutableLiveData()
    val selectedCustomer: LiveData<Customer> = _selectedCustomer

    fun loadCustomerList() {
        repository.fetchCustomerList()
    }

    fun getCustomerList(): LiveData<List<Customer>> {
        return repository.customerListLiveData()
    }

    fun selectCustomer(customer: Customer) {
        _selectedCustomer.postValue(customer)
    }
}