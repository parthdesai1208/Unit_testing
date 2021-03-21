package com.parthdesai1208.unit_testing

object Validator {
    fun validateInput(amount: Int, description: String) =
        !(amount <= 0 || description.isEmpty())
}