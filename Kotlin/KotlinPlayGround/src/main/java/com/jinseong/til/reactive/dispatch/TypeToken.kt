package com.jinseong.til.reactive.dispatch


/**
 * @author Jay
 */
class SuperTypeToken {


}

class Generic<T>(
    var value: T
) {

}


fun main() {
    val typeSafeMap = TypeSafeMap()
    typeSafeMap.put<String>(String::class.java, "asfsa")

    println(typeSafeMap.get(String::class.java ))
}


class TypeSafeMap {
    val map = mutableMapOf<Class<*>, Any?>()

    fun <T> put(clazz: Class<T>, value: T) {
        map[clazz] = value
    }

    fun <T> get(clazz: Class<T>): T {
        return clazz.cast(map.get(clazz))
    }
}


