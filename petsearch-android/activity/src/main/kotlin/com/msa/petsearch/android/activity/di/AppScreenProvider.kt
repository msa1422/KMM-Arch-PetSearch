package com.msa.petsearch.android.activity.di

import com.msa.petsearch.android.features.home.HomeNavRoute
import com.msa.petsearch.android.features.petdetail.PetDetailRoute

// Since NavRoute Objects are Singleton (Kotlin property)
// No need to inject them using any DI framework
val AppScreens = setOf(HomeNavRoute, PetDetailRoute)
