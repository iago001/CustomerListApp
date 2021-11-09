package com.iago.android.clist.customers.di

import com.iago.android.clist.BuildConfig
import com.iago.android.clist.customers.service.CustomerService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://androidbasics-e48d1.firebaseapp.com/"

@Module
@InstallIn(ActivityRetainedComponent::class)
class CustomerModule {

    @Provides
    fun provideCustomerService() : CustomerService {

        var clientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            clientBuilder.addNetworkInterceptor(HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            ))
        }


        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(clientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(CustomerService::class.java)
    }
}