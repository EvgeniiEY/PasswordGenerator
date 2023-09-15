package com.example.passwordgenerator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.passwordgenerator.PasswordGenerator.Companion.generators
import com.example.passwordgenerator.db.MyDbManager
import com.example.passwordgenerator.generators.LowerCaseGenerator
import com.example.passwordgenerator.generators.NumericCaseGenerator
import com.example.passwordgenerator.generators.SpecialCharGenerator
import com.example.passwordgenerator.generators.UpperCaseGenerator

class MainActivity : AppCompatActivity() {
    var passwordLength: EditText? = null
    //    var passwordlength = passwordLength?.text
    var textPasswordGenerated: TextView? = null
    var textErrorMessage: TextView? = null
    var lowerCaseCheckBox: CheckBox? = null
    var upperCaseCheckBox: CheckBox? = null
    var numericValueCheckBox: CheckBox? = null
    var specialCharsCheckBox: CheckBox? = null
    var btnGenerate: Button? = null
    var btnCopy: Button? = null
    var btnToDb: Button? = null
    val myDbManager = MyDbManager(this)
    var tvDbText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        clickListeners()

    }

    fun clickListeners() {


        btnGenerate?.setOnClickListener {


            if (passwordLength?.text?.isEmpty() == true) {
                textErrorMessage?.text = getString(R.string.emptyPassword)
                return@setOnClickListener
            }
            val passwordSize: Int = passwordLength?.text.toString().toInt()



            textErrorMessage?.text = ""
            if (passwordSize < 8) {
                textErrorMessage?.text = getString(R.string.shortPassword)
                return@setOnClickListener
            }
            // todo разобраться с наследованием и композицией

            generators.clear()
            if (lowerCaseCheckBox?.isChecked == true) PasswordGenerator.add(LowerCaseGenerator())
            if (upperCaseCheckBox?.isChecked == true) PasswordGenerator.add(UpperCaseGenerator())
            if (numericValueCheckBox?.isChecked == true) PasswordGenerator.add(
                NumericCaseGenerator()
            )
            if (specialCharsCheckBox?.isChecked == true) PasswordGenerator.add(
                SpecialCharGenerator()
            )

            if (generators.isEmpty()) {
                textErrorMessage?.text = getString(R.string.emptyCheckBoxes)
                return@setOnClickListener
            }


            var password = PasswordGenerator.generatePassword(passwordSize)
            textPasswordGenerated?.text = password

            tvDbText?.text = ""
            myDbManager.openDb()
            myDbManager.insertToDb(textPasswordGenerated?.text.toString())
            val dataList = myDbManager.readDbData()
            for(item in dataList)
            tvDbText?.append(item)
            tvDbText?.append("\n")

        }
        //todo: btnCopy не копирует в буфер --> сделать
        btnCopy?.setOnClickListener {
            val manager =
                getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            manager.setPrimaryClip(
                ClipData.newPlainText(
                    "password",
                    textPasswordGenerated?.text.toString()
                )
            )
            Toast.makeText(this, "Password Copied", Toast.LENGTH_SHORT).show()
        }
        btnToDb?.setOnClickListener {
            val intent = Intent(this, PswDbActivity::class.java)
            startActivity(intent)

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
        btnToDb = findViewById(R.id.buttonToDb)
        tvDbText = findViewById(R.id.tv_dbOfPasswords)

    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }


}
//    private fun generatePassword(passwordLength: Int): String {
//        var password = ""
//        var testPassed = true
//        var alphabetRange = mutableListOf<Char>()
//        var numberRange = mutableListOf<Char>()
//        var specialCharRange = mutableListOf<Char>()
//        var defaultSpecialChars = "^!$%&/()=?+*#_<>".toCharArray()
//
//        var alphabetSelected = lowerCaseCheckBox?.isChecked
//        if (alphabetSelected == true) for (lowerCaseChar in 'a'..'z') {
//            alphabetRange.add(lowerCaseChar)
//        }
//        var uppercaseAlphabetSelected = upperCaseCheckBox?.isChecked
//        if (uppercaseAlphabetSelected == true) for (upperCaseChar in 'A'..'Z') {
//            alphabetRange.add(upperCaseChar)
//        }
//        alphabetRange.shuffle()
//
//        var numericValueSelected = numericValueCheckBox?.isChecked
//        if (numericValueSelected == true) for (numbers in '0'..'9') {
//            numberRange.add(numbers)
//        }
//        numberRange.shuffle()
//
//        var specialCharsSelected = specialCharsCheckBox?.isChecked
//        if (specialCharsSelected == true) for (chars in defaultSpecialChars) {
//            specialCharRange.add(chars)
//        }
//        specialCharRange.shuffle()
//
//        var counter1: Byte = 0
//        while (counter1 < passwordLength) {
//            if (alphabetSelected == true && numericValueSelected == true) {
//                var randomRange = generateRandomNumber(0, 1)
//                if (randomRange == 0) {
//                    var randomIndex = generateRandomNumber(0, alphabetRange.size - 1)
//                    password += alphabetRange[randomIndex]
//                } else if (randomRange == 1) {
//                    var randomIndex = generateRandomNumber(0, numberRange.size - 1)
//                    password += numberRange[randomIndex]
//                }
//            } else if (alphabetSelected == true) {
//                var randomIndex = generateRandomNumber(0, alphabetRange.size - 1)
//                password += alphabetRange[randomIndex]
//            } else if (numericValueSelected == true) {
//                var randomIndex = generateRandomNumber(0, numberRange.size - 1)
//                password += numberRange[randomIndex]
//            }
//            counter1++
//        }
//
//        if (specialCharsCheckBox?.isChecked == true) {
//            var tempPassword = password.toCharArray()
//            for (i in tempPassword.indices) {
//                if (passwordLength.toShort() < 10) {
//                    var randomNum = generateRandomNumber(0, 2)
//                    if (randomNum > 1) {
//                        var randomIndex = generateRandomNumber(0, specialCharRange.size - 1)
//                        tempPassword[i] = specialCharRange[randomIndex]
//                    }
//                } else if (passwordLength.toShort() > 10) {
//                    var randomNum = generateRandomNumber(0, 3)
//                    if (randomNum > 1) {
//                        var randomIndex = generateRandomNumber(0, specialCharRange.size - 1)
//                        tempPassword[i] = specialCharRange[randomIndex]
//                    }
//                }
//            }
//            password = String(tempPassword)
//        }
//        if (alphabetSelected == true) {
//            val regex = Regex("[a-z]")
//            if (!regex.containsMatchIn(password)) {
//                testPassed = false
//            }
//        }
//
//        if (uppercaseAlphabetSelected == true) {
//            val regex = Regex("[A-Z]")
//            if (!regex.containsMatchIn(password)) {
//                testPassed = false
//            }
//        }
//
//        if (numericValueSelected == true) {
//            val regex = Regex("[0-9]")
//            if (!regex.containsMatchIn(password)) {
//                testPassed = false
//            }
//        }
//
//        return if (testPassed) {
//            password
//        } else "Error!"
//
//        textPasswordGenerated?.text = password
//    }
