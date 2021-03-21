package com.parthdesai1208.unit_testing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.parthdesai1208.unit_testing.data.SpendsTrackerDataSource

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val dataSource: SpendsTrackerDataSource) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SpendViewModel(dataSource) as T
    }
}
