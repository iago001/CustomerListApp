package com.iago.android.clist.customers.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.iago.android.clist.R
import com.iago.android.clist.customers.viewmodel.CustomerViewModel
import com.iago.android.clist.databinding.FragmentCustomerDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CustomerDetailsFragment : Fragment() {

    val viewModel: CustomerViewModel by activityViewModels()
    private lateinit var binding: FragmentCustomerDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_customer_details, container, false)
        binding = DataBindingUtil.bind(view.rootView)!!
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedCustomer.observe(viewLifecycleOwner) {
            binding.customer = it
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CustomerDetailsFragment()
    }
}