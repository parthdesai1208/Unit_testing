package com.parthdesai1208.unit_testing

object Validator {

    //Unit testing means testing particular unit/function/small feature

    //suppose we want to test that
    //user should enter >0 amount & not empty description
    fun validateInput(amount: Int, description: String): Boolean {
        return !(amount <= 0 || description.isEmpty())
    }
}