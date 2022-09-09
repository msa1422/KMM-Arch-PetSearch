package com.petsapp.petfinder.shared.core_util

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}