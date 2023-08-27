package com.example.passwordgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var password: String
        var passwordLength: String = "9"
        var passwordAlphabet: String
        var passwordNumbers: String
        var passwordCharsIncluded: String
        var passwordUppercase: String

        password = generatePassword(
            passwordLength,
            passwordAlphabet,
            passwordNumbers,
            passwordCharsIncluded,
            passwordUppercase
        )
    }

    fun generatePassword(
        passwordLength: String,
        passwordAlphabet: String,
        passwordNumbers: String,
        passwordCharsIncluded: String,
        passwordUppercase: String
    ): String {
        var password: String = ""
        var testPassed: Boolean = true

        var alphabetRange = mutableListOf<Char>()

        var alphabetSelected: Boolean = false
        var uppercaseAlphabetSelected: Boolean = false
        var numericValueSelected: Boolean = false
        var specialCharsSelected: Boolean = false

        var numberRange = mutableListOf<Char>()

        var specialCharRange = mutableListOf<Char>()

//        var additionalSpecialChars = passwordCharsIncluded.toCharArray()
        var defaultSpecialChars = "^!$%&/()=?+*#_<>".toCharArray()

        val lowerCaseCheckBox = findViewById<CheckBox>(R.id.lowerCase_checkBox);
        alphabetSelected = lowerCaseCheckBox.isChecked
        if (alphabetSelected) for (lowerCaseChar in 'a'..'z') {
            alphabetRange.add(lowerCaseChar)
        }
        val upperCaseCheckBox: CheckBox = findViewById(R.id.upperCase_checkBox);
        uppercaseAlphabetSelected = upperCaseCheckBox.isChecked
        if (uppercaseAlphabetSelected) for (upperCaseChar in 'A'..'Z') {
            alphabetRange.add(upperCaseChar)
        }
        alphabetRange.shuffle()


        val numericValueCheckBox: CheckBox = findViewById(R.id.numericValue_checkBox);
        numericValueSelected = numericValueCheckBox.isChecked
        if (numericValueSelected) for (numbers in '0'..'9') {
            numberRange.add(numbers)
        }
        numberRange.shuffle()


        val specialCharsCheckBox: CheckBox = findViewById(R.id.special_chars_checkBox);
        specialCharsSelected = specialCharsCheckBox.isChecked
        if (specialCharsSelected) for (chars in defaultSpecialChars) {
            specialCharRange.add(chars)
        }
        specialCharRange.shuffle()

        var counter1: Byte = 0
        while (counter1 < passwordLength.toByte()) {
            if (alphabetSelected && numericValueSelected) {
                var randomRange = generateRandomNumber(0, 1)
                if (randomRange == 0) {
                    var randomIndex = generateRandomNumber(0, alphabetRange.size - 1)
                    password += alphabetRange[randomIndex]
                } else if (randomRange == 1) {
                    var randomIndex = generateRandomNumber(0, numberRange.size - 1)
                    password += numberRange[randomIndex]
                }
            } else if (alphabetSelected) {
                var randomIndex = generateRandomNumber(0, alphabetRange.size - 1)
                password += alphabetRange[randomIndex]
            } else if (numericValueSelected) {
                var randomIndex = generateRandomNumber(0, numberRange.size - 1)
                password += numberRange[randomIndex]
            }
            counter1++
        }

        if (passwordCharsIncluded.isNotBlank()) {
            var tempPassword = password.toCharArray()
            for (i in tempPassword.indices) {
                if (passwordLength.toShort() < 10) {
                    var randomNum = generateRandomNumber(0, 2)
                    if (randomNum > 1) {
                        var randomIndex = generateRandomNumber(0, specialCharRange.size - 1)
                        tempPassword[i] = specialCharRange[randomIndex]
                    }
                } else if (passwordLength.toShort() > 10) {
                    var randomNum = generateRandomNumber(0, 3)
                    if (randomNum > 1) {
                        var randomIndex = generateRandomNumber(0, specialCharRange.size - 1)
                        tempPassword[i] = specialCharRange[randomIndex]
                    }
                }
            }
            password = String(tempPassword)
        }
        if (alphabetSelected) {
            val regex = Regex("[a-z]")
            if (!regex.containsMatchIn(password)) {
                testPassed = false
            }
        }

        if (uppercaseAlphabetSelected) {
            val regex = Regex("[A-Z]")
            if (!regex.containsMatchIn(password)) {
                testPassed = false
            }
        }

        if (numericValueSelected) {
            val regex = Regex("[0-9]")
            if (!regex.containsMatchIn(password)) {
                testPassed = false
            }
        }

        if (testPassed) {
            return password
        } else {
            return "Error!"
        }
    }

}

private fun generateRandomNumber(startValue: Int, endValue: Int): Int {
    return (startValue..endValue).random()
}
