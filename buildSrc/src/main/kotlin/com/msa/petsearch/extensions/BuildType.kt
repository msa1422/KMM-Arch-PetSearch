package com.msa.petsearch.extensions

internal interface BuildType {
    val isMinifyEnabled: Boolean
    val enableUnitTestCoverage: Boolean
    val enableAndroidTestCoverage: Boolean
}

internal object BuildTypeDebug : BuildType {
    override val isMinifyEnabled = false
    override val enableUnitTestCoverage = true
    override val enableAndroidTestCoverage = false

    const val applicationIdSuffix = ".debug"
    const val versionNameSuffix = "-DEBUG"
}

internal object BuildTypeBenchmark : BuildType {
    override val isMinifyEnabled = true
    override val enableUnitTestCoverage = false
    override val enableAndroidTestCoverage = false

    const val applicationIdSuffix = ".benchmark"
    const val versionNameSuffix = "-BENCHMARK"
}

internal object BuildTypeRelease : BuildType {
    override val isMinifyEnabled = true
    override val enableUnitTestCoverage = false
    override val enableAndroidTestCoverage = false
}
