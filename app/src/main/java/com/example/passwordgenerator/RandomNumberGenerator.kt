package com.example.passwordgenerator

object RandomNumberGenerator {
    fun generateRandomNumber(startValue: Int, endValue: Int): Int {
        return (startValue..endValue).random()
    }
}