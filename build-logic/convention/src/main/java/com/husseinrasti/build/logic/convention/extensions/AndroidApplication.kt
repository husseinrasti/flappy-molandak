package com.husseinrasti.build.logic.convention.extensions


import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import com.husseinrasti.build.logic.convention.base.BuildAndroidConfig
import com.husseinrasti.build.logic.convention.base.BuildProductDimensions
import com.husseinrasti.build.logic.convention.base.BuildType
import com.husseinrasti.build.logic.convention.base.BuildTypeDebug
import com.husseinrasti.build.logic.convention.base.BuildTypeRelease
import com.husseinrasti.build.logic.convention.base.ProductFlavorDevelop
import com.husseinrasti.build.logic.convention.base.ProductFlavorProduction
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.provideDelegate
import java.io.FileInputStream
import java.util.Properties

fun Project.loadAndroidApplication() {
    this.android(
        configureApp = {
            namespace = BuildAndroidConfig.APPLICATION_ID

            compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION
            defaultConfig.applicationId = BuildAndroidConfig.APPLICATION_ID
            defaultConfig.minSdk = BuildAndroidConfig.MIN_SDK_VERSION
            defaultConfig.targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION
            defaultConfig.multiDexEnabled = true
            defaultConfig.versionCode = BuildAndroidConfig.getVersionCode()
            defaultConfig.versionName = BuildAndroidConfig.getVersionName()
            defaultConfig.vectorDrawables.useSupportLibrary = BuildAndroidConfig.SUPPORT_LIBRARY_VECTOR_DRAWABLES
            defaultConfig.testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER

            applicationVariants.all {
                val variant = this
                variant.outputs
                    .map { it as BaseVariantOutputImpl }
                    .forEach { output ->
                        output.outputFileName = "FlappyMolandak_${variant.name}_v.${variant.versionName}.apk"
                    }
            }

            configureApplicationFlavor()

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

                jvmTarget = JavaVersion.VERSION_17.toString()
            }

            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }

            buildFeatures {
                compose = true
            }

            composeOptions {
                kotlinCompilerExtensionVersion = libs().findVersion("androidxComposeCompiler").get().toString()
            }

            testOptions {
                unitTests {
                    isReturnDefaultValues = true
                    isIncludeAndroidResources = true
                }
                animationsDisabled = true
            }
        }
    )

    kapt {
        correctErrorTypes = true
    }

    dependencies {
    }
}


internal fun Project.configureApplicationFlavor() {
    this.android(
        configureApp = {
            buildTypes.apply {
                getByName(BuildType.RELEASE) {
                    isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
                    isTestCoverageEnabled = BuildTypeRelease.isTestCoverageEnabled
                    isShrinkResources = BuildTypeRelease.isShrinkResources
                }
                getByName(BuildType.DEBUG) {
                    isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
                    isTestCoverageEnabled = BuildTypeDebug.isTestCoverageEnabled
                    isShrinkResources = BuildTypeDebug.isShrinkResources
                }
            }

            flavorDimensions.add(BuildProductDimensions.ENVIRONMENT)
            productFlavors.apply {
                ProductFlavorProduction.appCreate(this)
                ProductFlavorDevelop.appCreate(this)
            }
        }
    )
}