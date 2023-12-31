package com.example.passwordgenerator.generators

import com.example.passwordgenerator.PasswordGenerator


class UpperCaseGenerator : PasswordGenerator() {

    companion object {
        private const val CHAR_A = 'A'
        private const val CHAR_Z = 'Z'
    }


    override fun getChar(): String {
        return RandomGenerator.randomChar(CHAR_A.code, CHAR_Z.code).toChar().toString()

    }
}



