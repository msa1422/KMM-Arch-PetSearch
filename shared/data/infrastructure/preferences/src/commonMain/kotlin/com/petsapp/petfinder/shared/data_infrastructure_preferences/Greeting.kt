package com.petsapp.petfinder.shared.data_infrastructure_preferences

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}