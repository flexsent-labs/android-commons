package com.flexsentlabs.androidcommons.app.util


@Suppress("UNCHECKED_CAST")
class Parameters internal constructor(vararg val values: Any?) {

    private fun <T> elementAt(i: Int): T =
        if (values.size > i) values[i] as T else throw IllegalArgumentException("Can't get parameter value #$i from $this")

    operator fun <T> component1(): T = elementAt(0)
    operator fun <T> component2(): T = elementAt(1)
    operator fun <T> component3(): T = elementAt(2)
    operator fun <T> component4(): T = elementAt(3)
    operator fun <T> component5(): T = elementAt(4)
    operator fun <T> component6(): T = elementAt(5)
    operator fun <T> component7(): T = elementAt(6)
    operator fun <T> component8(): T = elementAt(7)
    operator fun <T> component9(): T = elementAt(8)
    operator fun <T> component10(): T = elementAt(9)

    /**
     * get element at given index
     * return T
     */
    operator fun <T> get(i: Int) = values[i] as T

    /**
     * Number of contained elements
     */
    fun size() = values.size

    /**
     * Tells if it has no parameter
     */
    fun isEmpty() = size() == 0

    /**
     * Tells if it has parameters
     */
    fun isNotEmpty() = !isEmpty()

    /**
     * Get first element of given type T
     * return T
     */
    inline fun <reified T> get() = values.first { it is T }

    companion object {
        const val MAX_PARAMS = 10
    }
}

fun parametersOf(vararg parameters: Any?): Parameters =
    if (parameters.size <= Parameters.MAX_PARAMS) Parameters(*parameters)
    else error("Can't build Parameters for more than ${Parameters.MAX_PARAMS} arguments")