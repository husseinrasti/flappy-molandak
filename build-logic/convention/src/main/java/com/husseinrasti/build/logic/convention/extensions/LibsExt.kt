package com.husseinrasti.build.logic.convention.extensions

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

fun Project.libs() =
    extensions.getByType<VersionCatalogsExtension>().named("libs")

fun Project.findLibrary(name: String) = libs().findLibrary(name).get()