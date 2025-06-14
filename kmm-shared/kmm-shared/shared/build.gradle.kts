import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework
import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("org.openapi.generator") version "7.13.0"
}

tasks.named<GenerateTask>("openApiGenerate") {
    generatorName.set("kotlin")
    library.set("multiplatform")
    inputSpec.set("${rootDir}/api/recipepuppy_openapi.yaml")
    outputDir.set("${rootDir}/generated")
    additionalProperties.set(mapOf("dateLibrary" to "kotlinx-datetime"))
}

kotlin {
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets["commonMain"].kotlin.srcDir("$rootDir/generated/src/commonMain/kotlin")

    val sharedXcf = XCFramework("shared")

    targets.withType<KotlinNativeTarget>().configureEach {
        binaries.framework {
            baseName = "shared"
            sharedXcf.add(this)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:2.3.5")
                implementation("io.ktor:ktor-client-content-negotiation:2.3.5")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.5")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
                implementation("io.ktor:ktor-client-darwin:2.3.5")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

gradle.projectsEvaluated {
    rootProject.extensions.extraProperties["kotlin.mpp.applyDefaultHierarchyTemplate"] = "false"
}