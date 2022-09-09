package com.petsapp.petfinder.shared.core_res

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}