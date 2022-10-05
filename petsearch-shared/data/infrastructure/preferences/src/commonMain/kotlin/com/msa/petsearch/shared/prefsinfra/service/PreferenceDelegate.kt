package com.msa.petsearch.shared.prefsinfra.service

import com.msa.petsearch.shared.coreutil.commonflow.CommonFlow
import com.msa.petsearch.shared.coreutil.commonflow.asCommonFlow
import com.msa.petsearch.shared.coreutil.resource.Resource
import com.msa.petsearch.shared.coreutil.resource.asResource
import com.msa.petsearch.shared.prefsinfra.service.PreferenceKeys.PREF_THEME_PRIMARY
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.getStringFlow
import com.russhwolf.settings.coroutines.toSuspendSettings
import kotlinx.coroutines.flow.distinctUntilChanged

@OptIn(ExperimentalSettingsApi::class)
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
