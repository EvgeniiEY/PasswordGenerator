package com.example.passwordgenerator


class NumericCaseGenerator : PasswordGenerator() {

    companion object {
        private const val CHAR_0 = '0'
        private const val CHAR_9 = '9'
    }


    override fun getChar(): String {
        return RandomGenerator.randomChar(CHAR_0.code, CHAR_9.code).toString()

    }
}



