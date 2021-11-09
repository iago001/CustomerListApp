package com.iago.android.clist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.iago.android.clist.customers.ui.fragments.CustomerDetailsFragment
import com.iago.android.clist.customers.ui.fragments.CustomerListFragment
import com.iago.android.clist.customers.viewmodel.CustomerViewModel
import com.iago.android.clist.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val viewModel: CustomerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
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