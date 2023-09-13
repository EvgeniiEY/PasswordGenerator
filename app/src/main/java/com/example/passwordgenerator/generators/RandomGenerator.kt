package com.example.passwordgenerator.generators

class RandomGenerator {

    companion object {
        fun generateRandomNumber(size: Int): Int {
            var random: Double = Math.random()
            return (random * size).toInt()
        }



        fun randomChar(min: Int, max: Int): Int {
            return generateRandomNumber(max - min + 1) + min
        }
    }
}