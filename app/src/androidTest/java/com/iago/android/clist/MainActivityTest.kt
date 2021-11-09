package com.iago.android.clist

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.iago.android.clist.customers.di.CustomerModule
import com.iago.android.clist.customers.service.CustomerService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.tls.HandshakeCertificates
import okhttp3.tls.HeldCertificate
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Thread.sleep
import java.net.InetAddress

@LargeTest
@UninstallModules(CustomerModule::class)
@HiltAndroidTest
class MainActivityTest {

    companion object {

        lateinit var mockWebServer: MockWebServer
        lateinit var baseUrl: String
        lateinit var localhostCertificate: HeldCertificate
        lateinit var serverCertificates: HandshakeCertificates
        lateinit var clientCertificates: HandshakeCertificates

        @BeforeClass
        @JvmStatic
        fun setup() {

            val localhost: String = InetAddress.getByName("localhost").getCanonicalHostName()
            localhostCertificate = HeldCertificate.Builder()
                .addSubjectAlternativeName(localhost)
                .build()

            serverCertificates = HandshakeCertificates.Builder()
                .heldCertificate(localhostCertificate)
                .build()

            clientCertificates = HandshakeCertificates.Builder()
                .addTrustedCertificate(localhostCertificate.certificate)
                .build()

            mockWebServer = MockWebServer()
            mockWebServer.useHttps(serverCertificates.sslSocketFactory(), false)
            mockWebServer.enqueue(
                MockResponse().setResponseCode(200)
                    .setBody(FileReader.getStringFromFile("customers.json"))
            )
            mockWebServer.start()

            baseUrl = mockWebServer.url("").toString()
        }
    }


    @Module
    @InstallIn(ActivityRetainedComponent::class)
    class TestModule {

        @Provides
        fun provideCustomerService(): CustomerService {
            var clientBuilder = OkHttpClient.Builder()

            clientBuilder.sslSocketFactory(
                clientCertificates.sslSocketFactory(),
                clientCertificates.trustManager
            )

            if (BuildConfig.DEBUG) {
                clientBuilder.addNetworkInterceptor(
                    HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                ))
            }


            val retrofit = Retrofit.Builder()
                .baseUrl(MainActivityTest.baseUrl)
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(CustomerService::class.java)
        }
    }

    @get:Rule
    var rule = RuleChain.outerRule(HiltAndroidRule(this)).
    around(ActivityTestRule(MainActivity::class.java))

    @Test
    fun mainActivityTest() {
        sleep(2000)

        val textView = onView(
            allOf(
                withId(R.id.customerNameTextView), withText("Russell Whyte"),
                withParent(withParent(withId(R.id.customer_list_recyclerview))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Russell Whyte")))

        val recyclerView = onView(
            allOf(
                withId(R.id.customer_list_recyclerview),
                childAtPosition(
                    withClassName(`is`("android.widget.FrameLayout")),
                    0
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(19, click()))

        val textView2 = onView(
            allOf(
                withId(R.id.textViewEmail), withText("Krista@example.com"),
                withParent(withParent(withId(R.id.fragment_container))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Krista@example.com")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }


}
