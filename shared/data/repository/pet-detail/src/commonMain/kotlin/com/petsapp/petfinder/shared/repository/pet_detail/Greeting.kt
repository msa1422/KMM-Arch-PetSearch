package com.petsapp.petfinder.shared.repository.pet_detail

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}