package com.parthdesai1208.unit_testing

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.parthdesai1208.unit_testing.data.SpendsDatabase
import com.parthdesai1208.unit_testing.data.SpendsTrackerDataSource

abstract class BaseFragment(@LayoutRes layout: Int) : Fragment(layout) {

    protected lateinit var viewModel: SpendViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = SpendsDatabase(requireContext())
        val dataSource = SpendsTrackerDataSource(db.getSpendDao())
        val factory = ViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, factory).get(SpendViewModel::class.java)
    }
}