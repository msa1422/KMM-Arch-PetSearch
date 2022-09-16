package com.msa.petsearch.extensions

interface BuildType {

    companion object {
        const val DEBUG = "debug"
        const val RELEASE = "release"
    }

    val isMinifyEnabled: Boolean
    val enableUnitTestCoverage: Boolean
}

object BuildTypeDebug : BuildType {
    override val isMinifyEnabled = false
    override val enableUnitTestCoverage = true

    const val applicationIdSuffix = ".debug"
    const val versionNameSuffix = "-DEBUG"
}

object BuildTypeRelease : BuildType {
    override val isMinifyEnabled = true
    override val enableUnitTestCoverage = false
}
