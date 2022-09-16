package com.msa.petsearch.shared.datainfrastructurepreferences.service

import com.msa.petsearch.shared.coreutil.CommonFlow
import com.msa.petsearch.shared.coreutil.asCommonFlow
import com.msa.petsearch.shared.coreutil.resource.Resource
import com.msa.petsearch.shared.coreutil.resource.asResource
import com.msa.petsearch.shared.datainfrastructurepreferences.service.PreferenceKeys.PREF_THEME_PRIMARY
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
