package com.iago.android.clist.customers.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iago.android.clist.customers.model.Customer
import com.iago.android.clist.customers.ui.viewholders.CustomerListViewHolder
import com.iago.android.clist.customers.ui.viewholders.ItemSelectionListener
import com.iago.android.clist.databinding.ItemCustomerListBinding

class CustomerListAdapter(private val customers: List<Customer>, val callback: ItemSelectionListener): RecyclerView.Adapter<CustomerListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCustomerListBinding.inflate(inflater, parent, false)
        return CustomerListViewHolder(binding, callback)
    }

    override fun getItemCount(): Int {
        return customers.size
    }

    override fun onBindViewHolder(holder: CustomerListViewHolder, position: Int) {
        holder.bind(customers[position])
    }
}