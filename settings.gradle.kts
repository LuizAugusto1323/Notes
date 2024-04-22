pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()

        plugins {
            id("com.google.devtools.ksp") version "1.6.20-1.0.5"
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "Notes"
include(":app")
