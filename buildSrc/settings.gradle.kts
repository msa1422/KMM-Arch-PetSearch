/**
 * Workaround to make version catalogs accessible from precompiled script plugins
 * https://github.com/gradle/gradle/issues/15383
 */
dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "buildSrc"
