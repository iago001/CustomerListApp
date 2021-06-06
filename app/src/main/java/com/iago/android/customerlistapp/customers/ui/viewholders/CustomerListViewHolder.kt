package com.iago.android.customerlistapp.customers.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.iago.android.customerlistapp.R
import com.iago.android.customerlistapp.customers.model.Customer
import com.iago.android.customerlistapp.databinding.ItemCustomerListBinding

class CustomerListViewHolder(private val binding: ItemCustomerListBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(customer: Customer) {
        binding.customerNameTextView.text =
            binding.root.context.getString(R.string.customer_name,
                customer.firstName, customer.lastName)

        binding.customerUserNameTextView.text = customer.userName
    }
}