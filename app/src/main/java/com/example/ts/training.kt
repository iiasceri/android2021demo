package com.example.ts

class MyObject {
    val bla = 3
}

interface ICallback {
    fun callback(o: MyObject)
}

class A : ICallback {
    private lateinit var o: MyObject
    val b = B(this, o)

    override fun callback(o: MyObject) {
        this.o = o
    }
}

class B(var ic: ICallback, o: MyObject) {

    init {
        Thread(Runnable {
            run {
                // some calculation
                ic.callback(o)
            }
        }).start();
    }
}

fun main() {
    val a = A()
    a.b.ic.callback(MyObject())
}