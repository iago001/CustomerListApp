package com.iago.android.customerlistapp.customers.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.iago.android.customerlistapp.customers.model.Customer
import com.iago.android.customerlistapp.customers.ui.adapter.CustomerListAdapter
import com.iago.android.customerlistapp.customers.viewmodel.CustomerViewModel
import com.iago.android.customerlistapp.databinding.FragmentCustomerListBinding

class CustomerListFragment : Fragment() {

    companion object {
        fun newInstance() = CustomerListFragment()
    }

    private lateinit var binding: FragmentCustomerListBinding
    private lateinit var viewModel: CustomerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCustomerListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)
        viewModel.loadCustomerList().observe(viewLifecycleOwner, fun(customers: List<Customer>) {
            val adapter = CustomerListAdapter(customers)
            binding.customerListRecyclerview.adapter = adapter
            binding.customerListRecyclerview.layoutManager = LinearLayoutManager(context)
        })
    }

}