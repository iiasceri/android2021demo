package com.example.ts


//Playground for kotlin concepts
interface MyContainer {
    var size: Int

    fun add(item: String) {
        // ...
        size += 1
    }
}

class MyContainerImpl : MyContainer {
    override var size: Int
        get() = 0
        set(value) { println("Just ignoring the $value") }
}

fun main() {

}
