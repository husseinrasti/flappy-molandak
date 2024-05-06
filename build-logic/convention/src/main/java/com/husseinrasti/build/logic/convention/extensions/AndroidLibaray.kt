package com.husseinrasti.build.logic.convention.extensions

import com.husseinrasti.build.logic.convention.base.BuildAndroidConfig
import com.husseinrasti.build.logic.convention.base.BuildProductDimensions
import com.husseinrasti.build.logic.convention.base.BuildType
import com.husseinrasti.build.logic.convention.base.BuildTypeDebug
import com.husseinrasti.build.logic.convention.base.BuildTypeRelease
import com.husseinrasti.build.logic.convention.base.ProductFlavorDevelop
import com.husseinrasti.build.logic.convention.base.ProductFlavorProduction
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.provideDelegate


fun Project.loadAndroidLibrary() {
    this.android(
        configureLib = {
            compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION
            defaultConfig.minSdk = BuildAndroidConfig.MIN_SDK_VERSION
            defaultConfig.targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION
            defaultConfig.multiDexEnabled = true

            configureLibraryFlavor()

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }

            kotlinOptions {
                // Treat all Kotlin warnings as errors (disabled by default)
                // Override by setting warningsAsErrors=true in your ~/.gradle/gradle.properties
                val warningsAsErrors: String? by project
                allWarningsAsErrors = warningsAsErrors.toBoolean()

                freeCompilerArgs = freeCompilerArgs + listOf(
                    "-opt-in=kotlin.RequiresOptIn",
                    // Enable experimental coroutines APIs, including Flow
                    "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                    "-opt-in=kotlinx.coroutines.FlowPreview",
                    "-opt-in=kotlin.Experimental",
                )

                // Set JVM target to 11
                jvmTarget = JavaVersion.VERSION_17.toString()
            }
        }
    )
}


internal fun Project.configureLibraryFlavor() {
    this.android(
        configureLib = {
            buildTypes.apply {
                getByName(BuildType.RELEASE) {
                    isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
                    isTestCoverageEnabled = BuildTypeRelease.isTestCoverageEnabled
                }
                getByName(BuildType.DEBUG) {
                    isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
                    isTestCoverageEnabled = BuildTypeDebug.isTestCoverageEnabled
                }
            }

            flavorDimensions.add(BuildProductDimensions.ENVIRONMENT)
            productFlavors.apply {
                ProductFlavorProduction.libraryCreate(this)
                ProductFlavorDevelop.libraryCreate(this)
            }
        }
    )
}
