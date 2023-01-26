package com.msa.petsearch.shared.data.infra.preferences.service

import com.msa.petsearch.shared.core.util.commonflow.asCommonFlow
import com.msa.petsearch.shared.core.util.resource.Resource
import com.msa.petsearch.shared.core.util.resource.asResource
import com.msa.petsearch.shared.data.infra.preferences.service.PreferenceKeys.PREF_THEME_PRIMARY
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.getStringFlow
import com.russhwolf.settings.coroutines.toSuspendSettings
import kotlinx.coroutines.flow.distinctUntilChanged

@OptIn(ExperimentalSettingsApi::class)
class PreferenceDelegate(private val settings: Settings) {

    private val suspendSettings by lazy { settings.toSuspendSettings() }

    suspend fun updateTheme(theme: String): Resource<Unit> =
        suspendSettings.putString(PREF_THEME_PRIMARY, theme).asResource()

    fun getThemeAsFlow() = (settings as ObservableSettings)
        .getStringFlow(PREF_THEME_PRIMARY, defaultValue = "")
        .distinctUntilChanged()
        .asCommonFlow()
}
