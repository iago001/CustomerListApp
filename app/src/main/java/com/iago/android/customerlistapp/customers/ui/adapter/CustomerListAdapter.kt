package com.iago.android.customerlistapp.customers.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iago.android.customerlistapp.customers.model.Customer
import com.iago.android.customerlistapp.customers.ui.viewholders.CustomerListViewHolder
import com.iago.android.customerlistapp.databinding.ItemCustomerListBinding

class CustomerListAdapter(private val customers: List<Customer>): RecyclerView.Adapter<CustomerListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCustomerListBinding.inflate(inflater, parent, false)
        return CustomerListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return customers.size
    }

    override fun onBindViewHolder(holder: CustomerListViewHolder, position: Int) {
        holder.bind(customers[position])
    }

}