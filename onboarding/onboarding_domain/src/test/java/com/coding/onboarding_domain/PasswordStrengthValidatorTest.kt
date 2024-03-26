package com.coding.onboarding_domain

import com.coding.onboarding_domain.use_case.PasswordStrengthValidator
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PasswordStrengthValidatorTest {

    val passwordStrengthValidator = PasswordStrengthValidator()
    @Test
    fun minimum_6_charectors_required() {
        assertTrue(passwordStrengthValidator("12345","12345").contains(PasswordStrengthValidator.ERR_LEN))
    }

    @Test
    fun atleast_one_digit_required() {
        assertTrue(passwordStrengthValidator("abcdefg","abcdefg").contains(PasswordStrengthValidator.ERR_DIGIT))
    }

    @Test
    fun atleast_one_letter_required() {
        assertTrue(passwordStrengthValidator("123456","123456").contains(PasswordStrengthValidator.ERR_LETTER))
    }

    @Test
    fun password_and_confirm_password_should_match() {
        assertTrue(passwordStrengthValidator("123456","123457").contains(PasswordStrengthValidator.ERR_CONFIRM_PASS))
    }
}