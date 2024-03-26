package com.coding.onboarding_domain.use_case

class PasswordStrengthValidator {

    operator fun invoke(password: String, confirmPassword: String): List<String> {
        return listOfNotNull(
            checkFor(password != confirmPassword, ERR_CONFIRM_PASS),
            checkFor(password.length <= 5, ERR_LEN),
            checkFor(!password.any { it.isDigit() }, ERR_DIGIT),
            checkFor(!password.any { it.isLetter() }, ERR_LETTER)
        )
    }

    private fun checkFor(condition: Boolean, error: String):String?{
        return if (condition) {
            error
        } else
            null
    }

    companion object {
        const val ERR_CONFIRM_PASS = "Password and confirm password do not match!"
        const val ERR_LEN = "Password must have at least six characters!"
        const val ERR_DIGIT = "Password must contain at least one digit!"
        const val ERR_LETTER = "Password must have at least one letter!"
    }

}