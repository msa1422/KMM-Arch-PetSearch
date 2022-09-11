package com.petsapp.petfinder.shared.domain.pet_detail_data_source

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
