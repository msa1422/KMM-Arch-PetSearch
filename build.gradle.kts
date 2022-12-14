buildscript {
    repositories.applyDefault()

    plugins.apply("com.msa.petsearch.checks.detekt")
    plugins.apply("com.msa.petsearch.checks.ktlint")
    plugins.apply("com.msa.petsearch.checks.spotless")
    plugins.apply("com.msa.petsearch.checks.dependency-updates")
}
