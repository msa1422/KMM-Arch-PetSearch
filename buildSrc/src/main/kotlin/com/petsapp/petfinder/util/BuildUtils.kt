package com.petsapp.petfinder.util

import org.gradle.api.Project
import java.util.Properties

private const val LOCAL_PROPERTIES_FILE_NAME = "local.properties"
private const val KEYSTORE_PROPERTIES_FILE_NAME = "keystore.properties"
private const val API_PROPERTIES_FILE_NAME = "apikey.properties"

/**
 * Util to obtain property declared on `$projectRoot/local.properties` file.
 *
 * @param propertyName the name of declared property
 * @param project the project reference
 *
 * @return the value of property name, otherwise throw [Exception]
 */
fun getLocalProperty(propertyName: String, project: Project): String {
    val localProperties = Properties().apply {
        val localPropertiesFile = project.rootProject.file(LOCAL_PROPERTIES_FILE_NAME)
        if (localPropertiesFile.exists()) {
            load(localPropertiesFile.inputStream())
        }
    }

    return localProperties.getProperty(propertyName) ?: run {
        throw NoSuchFieldException("Not defined property: $propertyName")
    }
}

fun getKeystoreProperty(propertyName: String, project: Project): String {
    val keystoreProperties = Properties().apply {
        val keystorePropertiesFile = project.rootProject.file(KEYSTORE_PROPERTIES_FILE_NAME)
        if (keystorePropertiesFile.exists()) {
            load(keystorePropertiesFile.inputStream())
        }
    }

    return keystoreProperties.getProperty(propertyName) ?: run {
        throw NoSuchFieldException("Not defined property: $propertyName")
    }
}

fun getApiProperty(propertyName: String, project: Project): String {
    val localProperties = Properties().apply {
        val localPropertiesFile = project.rootProject.file(API_PROPERTIES_FILE_NAME)
        if (localPropertiesFile.exists()) {
            load(localPropertiesFile.inputStream())
        }
    }

    return localProperties.getProperty(propertyName) ?: run {
        throw NoSuchFieldException("Not defined property: $propertyName")
    }
}
