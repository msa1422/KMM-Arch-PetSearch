package com.msa.petsearch.activity.di

import com.msa.petsearch.home.HomeNavRoute
import com.msa.petsearch.petdetail.PetDetailRoute

// Since NavRoute Objects are Singleton (Kotlin property)
// No need to inject them using any DI framework
val AppScreens = setOf(HomeNavRoute, PetDetailRoute)
