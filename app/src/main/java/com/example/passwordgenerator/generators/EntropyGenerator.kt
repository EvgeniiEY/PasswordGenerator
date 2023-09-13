package com.example.passwordgenerator.generators

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.log2

class EntropyGenerator {
    fun entropyCalculation() {
        var l = 10 //длина пароля
        var r = 62.0 // диапазон символов
        var e = entropy(l, r)   // энтропия
        var decimal = BigDecimal(e).setScale(2, RoundingMode.HALF_UP)

        print("Entropy: " + decimal)

    }

    //вычисляет показатель энтропии
    fun entropy(l: Int, r: Double): Double {

        return l * log2(r)
    }
}