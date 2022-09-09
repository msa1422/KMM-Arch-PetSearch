package com.petsapp.petfinder.shared.data_infrastructure_cache

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}