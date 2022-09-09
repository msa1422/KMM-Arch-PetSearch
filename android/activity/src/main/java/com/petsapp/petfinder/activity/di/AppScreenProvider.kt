package com.petsapp.petfinder.activity.di

import com.petsapp.petfinder.home.HomeNavRoute
import com.petsapp.petfinder.pet_detail.PetDetailRoute

// Since NavRoute Objects are Singleton (Kotlin property)
// No need to inject them using any DI framework
fun provideAppScreens() = setOf(HomeNavRoute, PetDetailRoute)