package com.petsapp.petfinder.shared.data_infrastructure_preferences.service

import com.petsapp.petfinder.shared.core_util.CommonFlow
import com.petsapp.petfinder.shared.core_util.asCommonFlow
import com.petsapp.petfinder.shared.core_util.resource.Resource
import com.petsapp.petfinder.shared.core_util.resource.asResource
import com.petsapp.petfinder.shared.data_infrastructure_preferences.service.PreferenceKeys.PREF_THEME_PRIMARY
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.getStringFlow
import com.russhwolf.settings.coroutines.toSuspendSettings
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.distinctUntilChanged

@OptIn(ExperimentalSettingsApi::class, ExperimentalCoroutinesApi::class)
class PreferenceDelegate(private val settings: Settings) {

    private val suspendSettings by lazy { settings.toSuspendSettings() }

    suspend fun updateTheme(theme: String): Resource<Boolean> {
        return try {
            suspendSettings.putString(PREF_THEME_PRIMARY, theme)
            true
        }
        catch (e: IllegalArgumentException) {
            false
        }.asResource { it }
    }

    fun getThemeAsFlow(): CommonFlow<String> {
        return (settings as ObservableSettings)
            .getStringFlow(PREF_THEME_PRIMARY)
            .distinctUntilChanged()
            .asCommonFlow()
    }

}
