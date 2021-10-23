package com.iago.android.clist.customers.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.iago.android.clist.customers.model.Customer
import com.iago.android.clist.customers.ui.adapters.CustomerListAdapter
import com.iago.android.clist.customers.ui.viewholders.ItemSelectionListener
import com.iago.android.clist.customers.viewmodel.CustomerViewModel
import com.iago.android.clist.databinding.FragmentCustomerListBinding

class CustomerListFragment : Fragment() {

    companion object {
        fun newInstance() = CustomerListFragment()
    }

    private val viewModel: CustomerViewModel by activityViewModels()
    private lateinit var binding: FragmentCustomerListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomerListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCustomerList().observe(viewLifecycleOwner, fun(it: List<Customer>) {
            val adapter = CustomerListAdapter(it, object : ItemSelectionListener {
                override fun onItemSelected(customer: Customer) {
                    viewModel.selectCustomer(customer)
                }
            })
            binding.customerListRecyclerview.adapter = adapter
            binding.customerListRecyclerview.layoutManager = LinearLayoutManager(context)
        })
        viewModel.loadCustomerList()
    }

}