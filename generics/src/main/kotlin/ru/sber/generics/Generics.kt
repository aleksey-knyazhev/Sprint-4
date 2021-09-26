package ru.sber.generics

import java.util.*

// 1.
fun <K, V> compare(p1: Pair<K, V>, p2: Pair<K, V>): Boolean {
    return p1 == p2
}

// 2.
fun <T: Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
    var result = 0

    for (i in anArray) {
        if (i > elem)
            result++
    }

    return result
}

// 3.
class Sorter<E: Comparable<E>> {
    val list: MutableList<E> = mutableListOf()

    fun add(value: E) {
        list.add(value)
        list.sort()
    }
}

// 4.
class Stack<T> {
    val stack = mutableListOf<T>()

    fun isEmpty() = stack.isEmpty()

    fun push(stackElement: T) = stack.add(stackElement)

    fun pop(): T = stack.removeLast()

}