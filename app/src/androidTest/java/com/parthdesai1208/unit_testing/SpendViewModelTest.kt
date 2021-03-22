package com.parthdesai1208.unit_testing

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.parthdesai1208.unit_testing.data.SpendsDatabase
import com.parthdesai1208.unit_testing.data.SpendsTrackerDataSource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SpendViewModelTest {
    private lateinit var viewModel: SpendViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule() //it swaps background executor
    //for executing task synchronously

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = Room.inMemoryDatabaseBuilder(context, SpendsDatabase::class.java)
            .allowMainThreadQueries().build()
        val dataSource = SpendsTrackerDataSource(db.getSpendDao())
        viewModel = SpendViewModel(dataSource)
    }

    @Test
    fun testSpendViewModelWithRightData() {
        //1) first we add fake data to viewModel
        //2) then get data from viewModel using getLast20Spends() method
        //2.1) it will store data to liveData
        //3) then we get data from liveData and check if our data is present in it or not
        viewModel.addSpend(170, "Bought some flowers")
        viewModel.getLast20Spends()
        val result = viewModel.last20SpendsLiveData.getOrAwaitValue().find {
            it.amount == 170 && it.description == "Bought some flowers"
        }
        assertThat(result != null).isTrue()
    }

    @Test
    fun testSpendViewModelWithWrongData() {
        //1) first we add fake data to viewModel
        //2) then get data from viewModel using getLast20Spends() method
        //2.1) it will store data to liveData
        //3) then we get data from liveData and check if our data is present in it or not
        viewModel.addSpend(171, "Bought some flowers")
        viewModel.getLast20Spends()
        val result = viewModel.last20SpendsLiveData.getOrAwaitValue().find {
            it.amount == 170 && it.description == "Bought some flowers"
        }
        assertThat(result != null).isFalse()
    }

}