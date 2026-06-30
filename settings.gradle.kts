enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Pizzeria"
include(":app")
include(":catalog:presentation")
include(":catalog:domain")
include(":catalog:data")
include(":cart:presentation")
include(":cart:domain")
include(":cart:data")
include(":orders:presentation")
include(":orders:domain")
include(":orders:data")
include(":profile:presentation")
include(":profile:data")
include(":profile:domain")
include(":auth:presentation")
include(":auth:data")
include(":auth:domain")
include(":core:ui")
include(":core:network")
include(":core:errors")
include(":core:storage")
