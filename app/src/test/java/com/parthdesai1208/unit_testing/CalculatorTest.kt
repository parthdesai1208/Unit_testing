package com.parthdesai1208.unit_testing

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test


class CalculatorTest {

    var c: Calculator? = null

    @Before
    fun setUp(){
        c = Calculator()
    }

    @Test
    fun testAdd(){
        assertThat(c?.add(2,3)).isEqualTo(5)
        assertThat(c?.add(2,3)).isNotEqualTo(4)
    }

}