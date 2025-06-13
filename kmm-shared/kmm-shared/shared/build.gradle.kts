/* ---------- imports: без них XCFramework и KotlinNativeTarget не видны ---------- */
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework
import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.serializationPlugin)
    id("org.openapi.generator") version "7.13.0"
}

tasks.named<GenerateTask>("openApiGenerate") {
    generatorName.set("shared")
    library.set("multiplatform")
    inputSpec.set("${rootDir}/api/recipepuppy_openapi.yaml")
    outputDir.set("${rootDir}/generated")
    additionalProperties.set(
        mapOf(
            "dateLibrary" to "kotlinx-datetime"
        )
    )
}

kotlin {
    /* ---------- iOS + Android targets ---------- */
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    // Подключение папки с автогенерированным кодом в общий sourceSet
    sourceSets["commonMain"].kotlin.srcDir("$rootDir/generated/src/commonMain/kotlin")

    /* ---------- агрегатор .xcframework ---------- */
    val sharedXcf = XCFramework()

    targets.withType<KotlinNativeTarget>().configureEach {
        binaries.framework {
            baseName = "shared"
            sharedXcf.add(this)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.kotlinx.serialization.json)
                implementation("io.ktor:ktor-client-darwin:3.0.3")
            }
        }
        val androidMain by getting { dependencies { implementation(libs.ktor.client.okhttp) } }
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

/* опционально: убираем ворчание о default-template */
gradle.projectsEvaluated {
    rootProject.extensions.extraProperties["kotlin.mpp.applyDefaultHierarchyTemplate"] = "false"
}

android {
    namespace = "com.example.shared"
    compileSdk = 34
    defaultConfig { minSdk = 24 }
}