package com.iago.android.clist.customers.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(application: Application) : ViewModelProvider.NewInstanceFactory() {

    val _application: Application = application

    @NonNull
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  CustomerViewModel() as T
    }
}