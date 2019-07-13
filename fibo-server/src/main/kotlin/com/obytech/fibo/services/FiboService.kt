package com.obytech.fibo.services

import java.math.BigInteger
import java.util.concurrent.atomic.AtomicInteger

object FiboService {

    var previous = BigInteger("0")
    var current = BigInteger("1")
    var iterations = AtomicInteger(1)

    fun getNextFibo(): BigInteger {
        val value = previous.add(current)
        iterations.getAndIncrement()

        previous = current
        current = value

        return value
    }

    fun init(iterations: Int) {
        reset()

        for (i in 0..iterations) getNextFibo()
    }

    fun reset() {
        previous = BigInteger("0")
        current = BigInteger("1")
        this.iterations = AtomicInteger(1)
    }

    fun getNextFiboWithIterations() = Pair(getNextFibo(), iterations.get())

}

fun main() {
    for (i in 0..100) {
        val (fibo, iter) = FiboService.getNextFiboWithIterations()
        println("$iter: $fibo")
    }
}