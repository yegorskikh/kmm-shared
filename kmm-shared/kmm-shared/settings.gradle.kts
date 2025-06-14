rootProject.name = "kmm-shared"

pluginManagement {
    plugins {
        id("com.android.library") version "8.4.2"
        id("org.jetbrains.kotlin.multiplatform") version "2.1.21"
        id("org.jetbrains.kotlin.plugin.serialization") version "2.1.21"
    }
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

include(":shared")