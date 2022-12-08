package com.msa.petsearch.extensions

interface BuildType {
    val isMinifyEnabled: Boolean
    val enableUnitTestCoverage: Boolean
    val enableAndroidTestCoverage: Boolean
}

object BuildTypeDebug : BuildType {
    override val isMinifyEnabled = false
    override val enableUnitTestCoverage = true
    override val enableAndroidTestCoverage = true

    const val applicationIdSuffix = ".debug"
    const val versionNameSuffix = "-DEBUG"
}

object BuildTypeBenchmark : BuildType {
    override val isMinifyEnabled = true
    override val enableUnitTestCoverage = false
    override val enableAndroidTestCoverage = false

    const val applicationIdSuffix = ".benchmark"
    const val versionNameSuffix = "-BENCHMARK"
}

object BuildTypeRelease : BuildType {
    override val isMinifyEnabled = true
    override val enableUnitTestCoverage = false
    override val enableAndroidTestCoverage = false
}
