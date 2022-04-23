package com.jinseong.til.reactive.dispatch

import java.lang.reflect.ParameterizedType


/**
 * @author Jay
 */
open class Super<T>(
    var value: T
) {

}

//class Sub(
//    var name: String
//): Super<List<String>>() {
//
//}

fun main() {
    val value = Super<String>("fsaf")

    //컴파일 하게 되면 Sup<>() 로 만들어짐
    println(value.javaClass.getDeclaredField("value").type)

//    val sub = Sub("ss")
//    println(sub.javaClass.getDeclaredField("name").type)
//    val t = sub.javaClass.genericSuperclass
//    val parameterizedType = t as ParameterizedType
//    println(parameterizedType.actualTypeArguments.first())
}
