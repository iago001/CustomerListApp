package com.iago.android.customerlistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.iago.android.customerlistapp.customers.ui.fragments.CustomerListFragment
import com.iago.android.customerlistapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val fragment = CustomerListFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commitAllowingStateLoss()
    }
}