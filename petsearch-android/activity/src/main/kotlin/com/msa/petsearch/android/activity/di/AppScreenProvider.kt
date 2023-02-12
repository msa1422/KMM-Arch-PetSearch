package com.msa.petsearch.android.activity.di

import com.msa.petsearch.android.features.home.HomeNavRoute2
import com.msa.petsearch.android.features.petdetail.PetDetailRoute2

// Since NavRoute Objects are Singleton (Kotlin property)
// No need to inject them using any DI framework
val AppScreens = setOf(HomeNavRoute2, PetDetailRoute2)
