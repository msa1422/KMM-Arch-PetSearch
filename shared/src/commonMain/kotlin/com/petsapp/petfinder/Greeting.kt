package com.petsapp.petfinder

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}