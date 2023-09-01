package com.example.passwordgenerator

import com.example.passwordgenerator.generators.LowerCaseGenerator

abstract class PasswordGenerator {

    companion object {
        var generators: MutableList<PasswordGenerator> = mutableListOf()


        fun clear() {
            if (generators != emptyArray<PasswordGenerator>()) {
                generators.clear()
            } else generators
        }




        fun isEmpty() {
            generators.isEmpty()
        }

        fun add(generatedPass: PasswordGenerator) {
            generators.add(generatedPass)
        }


        private fun getRandomPassGen(): PasswordGenerator {
            if (generators == null) {
                generators = ArrayList()
                add(LowerCaseGenerator())
            }
            if (generators.size == 1) return generators[0]
            val randomIndex: Int = RandomGenerator.generateRandomNumber(generators.size)
            return generators[randomIndex]
        }

        fun generatePassword(sizeOfPassword: Int): String {
            var sizeOfPassword = sizeOfPassword
            val password = StringBuilder()

            while (sizeOfPassword != 0) {
                password.append(getRandomPassGen().getChar())
                sizeOfPassword--
            }
            return password.toString()
        }
    }

    abstract fun getChar():String

}
