package com.petsapp.petfinder.shared.data_infrastructure_network.api_service

internal object EndPoints {
    const val API_HOST = "api.petfinder.com"

    const val ACCESS_TOKEN = "v2/oauth2/token"
    const val ANIMALS = "v2/animals"
    const val ANIMAL_DETAIL = "v2/animals/{id}"
    const val ANIMAL_TYPES = "v2/types"
    const val SINGLE_ANIMAL_TYPE = "v2/types/{type}"
    const val ANIMAL_BREEDS = "v2/types/{type}/breeds"
}
