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
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "FeatureModules"
include(":app")
include(":core:common")
include(":core:commonImpl")
include(":features:sign-in")
include(":core:presentation")
include(":core:theme")
include(":navigation")
include(":wiring")
include(":data")
include(":features:sign-up")
include(":features:catalog")
include(":features:cart")
include(":features:profile")
