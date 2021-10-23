package com.iago.android.clist.customers.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.iago.android.clist.R
import com.iago.android.clist.customers.model.Customer
import com.iago.android.clist.databinding.ItemCustomerListBinding

class CustomerListViewHolder(private val binding: ItemCustomerListBinding, val callback: ItemSelectionListener): RecyclerView.ViewHolder(binding.root) {

    fun bind(customer: Customer) {
        binding.customer = customer
        binding.root.setOnClickListener {
            callback.onItemSelected(customer)
        }
    }
}

interface ItemSelectionListener {
    fun onItemSelected(customer: Customer)
}