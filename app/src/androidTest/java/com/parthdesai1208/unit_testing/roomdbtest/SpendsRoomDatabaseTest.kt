package com.parthdesai1208.unit_testing.roomdbtest

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.parthdesai1208.unit_testing.data.Spend
import com.parthdesai1208.unit_testing.data.SpendDao
import com.parthdesai1208.unit_testing.data.SpendsDatabase
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

//here we use AndroidJUnit4 class because we need android context/device to test room db
@RunWith(AndroidJUnit4::class)
class SpendsRoomDatabaseTest {

    private lateinit var db: SpendsDatabase
    private lateinit var dao: SpendDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, SpendsDatabase::class.java).build()
        //inMemoryDatabaseBuilder means temporary database
        //Information stored in an in memory/database disappears when the process is killed.
        dao = db.getSpendDao()
    }

    @After
    fun closeDb() {
        //after test we need to close database
        db.close()
    }

    //whatever function you need to test
    //define it as @Test
    @Test
    fun writeAndReadSpend() =
        runBlocking {  //we use runBlocking because of addSpend() & getLast20Spends() is suspend function
            val date = Date()
            val spend = Spend(date, 100, "Bought something")
            dao.addSpend(spend)
            val spends = dao.getLast20Spends()
            assertThat(spends.contains(spend)).isTrue()
        }
}