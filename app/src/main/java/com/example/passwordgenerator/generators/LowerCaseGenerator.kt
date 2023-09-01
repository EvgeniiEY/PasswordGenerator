package com.example.passwordgenerator.generators

import com.example.passwordgenerator.PasswordGenerator
import com.example.passwordgenerator.RandomGenerator


class LowerCaseGenerator : PasswordGenerator() {

    companion object {
        private const val CHAR_A = 'a'
        private const val CHAR_Z = 'z'
    }



    override fun getChar(): String {
//        return RandomGenerator.randomChar(CHAR_A, CHAR_Z).toString()

        return (RandomGenerator.randomChar(CHAR_A.code, CHAR_Z.code).toChar().toString())

    }
}



