package com.my.myemployeestesttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.my.core.ProvideComponent

internal class MainActivity : AppCompatActivity() {

    private val navigation by lazy {
        (application as ProvideComponent)
            .provideComponent<AppComponent>()
            .provideAppNavigation()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        navigation.initFragmentFactory(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.subscribe()
    }

    override fun onDestroy() {
        super.onDestroy()
        navigation.unsubscribe()
    }
}