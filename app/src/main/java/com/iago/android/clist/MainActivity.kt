package com.iago.android.clist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.iago.android.clist.customers.ui.fragments.CustomerDetailsFragment
import com.iago.android.clist.customers.ui.fragments.CustomerListFragment
import com.iago.android.clist.customers.viewmodel.CustomerViewModel
import com.iago.android.clist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CustomerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val fragment = CustomerListFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commitAllowingStateLoss()

        viewModel.selectedCustomer.observe(this) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, CustomerDetailsFragment.newInstance())
                .addToBackStack("stack")
                .commitAllowingStateLoss()
        }
    }
}