package com.example.passwordgenerator.generators

import com.example.passwordgenerator.PasswordGenerator


class SpecialCharGenerator : PasswordGenerator() {

    companion object {
        var SPECIAL_CHAR_ARRAY = "?./!%*$^+-)]@(['{}#<>".toCharArray()
    }


    override fun getChar(): String {

       return SPECIAL_CHAR_ARRAY[RandomGenerator.randomChar(0, SPECIAL_CHAR_ARRAY.size - 1)].toString()
    }
}



