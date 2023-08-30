package com.example.passwordgenerator


class LowerCaseGenerator : PasswordGenerator() {

    companion object {
        private const val CHAR_A = 'a'
        private const val CHAR_Z = 'z'
    }


    override fun getChar(): String {
        return RandomGenerator.randomChar(CHAR_A.code, CHAR_Z.code).toString()

    }
}



