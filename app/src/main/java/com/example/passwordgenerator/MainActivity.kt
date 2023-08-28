package com.example.passwordgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.example.passwordgenerator.RandomNumberGenerator.generateRandomNumber

class MainActivity : AppCompatActivity() {
    var passwordLength: EditText? = null
    var textPasswordGenerated: TextView? = null
    var textErrorMessage: TextView? = null
    var lowerCaseCheckBox: CheckBox? = null
    var upperCaseCheckBox: CheckBox? = null
    var numericValueCheckBox: CheckBox? = null
    var specialCharsCheckBox: CheckBox? = null
    var btnGenerate: Button? = null
    var btnCopy: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    fun clickListeners() {
        btnGenerate?.setOnClickListener {
            val passwordSize: Int = passwordLength?.text.toString().toInt()
            textErrorMessage?.text = ""
            if (passwordSize < 8) {
                textErrorMessage?.text = "Password must be greater than 8"
                return@setOnClickListener
            }
        }

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
        var numberRange = mutableListOf<Char>()
        var specialCharRange = mutableListOf<Char>()
        var defaultSpecialChars = "^!$%&/()=?+*#_<>".toCharArray()

        var alphabetSelected = lowerCaseCheckBox?.isChecked
        if (alphabetSelected == true) for (lowerCaseChar in 'a'..'z') {
            alphabetRange.add(lowerCaseChar)
        }
        var uppercaseAlphabetSelected = upperCaseCheckBox?.isChecked
        if (uppercaseAlphabetSelected == true) for (upperCaseChar in 'A'..'Z') {
            alphabetRange.add(upperCaseChar)
        }
        alphabetRange.shuffle()

        var numericValueSelected = numericValueCheckBox?.isChecked
        if (numericValueSelected == true) for (numbers in '0'..'9') {
            numberRange.add(numbers)
        }
        numberRange.shuffle()

        var specialCharsSelected = specialCharsCheckBox?.isChecked
        if (specialCharsSelected == true) for (chars in defaultSpecialChars) {
            specialCharRange.add(chars)
        }
        specialCharRange.shuffle()

        var counter1: Byte = 0
        while (counter1 < passwordLength.toByte()) {
            if (alphabetSelected == true && numericValueSelected == true) {
                var randomRange = generateRandomNumber(0, 1)
                if (randomRange == 0) {
                    var randomIndex = generateRandomNumber(0, alphabetRange.size - 1)
                    password += alphabetRange[randomIndex]
                } else if (randomRange == 1) {
                    var randomIndex = generateRandomNumber(0, numberRange.size - 1)
                    password += numberRange[randomIndex]
                }
            } else if (alphabetSelected == true) {
                var randomIndex = generateRandomNumber(0, alphabetRange.size - 1)
                password += alphabetRange[randomIndex]
            } else if (numericValueSelected == true) {
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
        if (alphabetSelected == true) {
            val regex = Regex("[a-z]")
            if (!regex.containsMatchIn(password)) {
                testPassed = false
            }
        }

        if (uppercaseAlphabetSelected == true) {
            val regex = Regex("[A-Z]")
            if (!regex.containsMatchIn(password)) {
                testPassed = false
            }
        }

        if (numericValueSelected == true) {
            val regex = Regex("[0-9]")
            if (!regex.containsMatchIn(password)) {
                testPassed = false
            }
        }

        return if (testPassed) {
            password
        } else {
            "Error!"
        }
    }

    private fun initViews() {

        passwordLength = findViewById(R.id.passwordSize_et)
        textPasswordGenerated = findViewById(R.id.password_generated_tv)
        textErrorMessage = findViewById(R.id.error_tv)
        lowerCaseCheckBox = findViewById(R.id.lowerCase_checkBox)
        upperCaseCheckBox = findViewById(R.id.upperCase_checkBox)
        numericValueCheckBox = findViewById(R.id.numericValue_checkBox)
        specialCharsCheckBox = findViewById(R.id.special_chars_checkBox)
        btnGenerate = findViewById(R.id.buttonGenerate)
        btnCopy = findViewById(R.id.buttonCopy)

    }
}