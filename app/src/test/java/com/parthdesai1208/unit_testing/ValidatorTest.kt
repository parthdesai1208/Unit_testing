package com.parthdesai1208.unit_testing

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidatorTest {


    //whatever function you need to test
    //define it as @Test
    @Test
    fun whenInputIsValid() {
        val amount = 99
        val desc = "some random description"
        val result = Validator.validateInput(amount, desc)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenInputIsInValid() {
        val amount = 0
        val desc = ""
        val result = Validator.validateInput(amount, desc)
        assertThat(result).isEqualTo(false)
    }
}