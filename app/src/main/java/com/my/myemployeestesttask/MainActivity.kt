package com.my.myemployeestesttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.my.employees.EmployeesNavigation

class MainActivity : AppCompatActivity(), EmployeesNavigation {

    override fun onCreate(savedInstanceState: Bundle?) {
        val fragmentFactory = (application as AppComponentProvider)
            .provide().provideEmployeesFragmentFactory()
        supportFragmentManager.fragmentFactory = fragmentFactory

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.content, fragmentFactory.employeesFragment())
                .commit()
        }
    }

    override fun toEmployeeDetails(id: String) = Unit
}