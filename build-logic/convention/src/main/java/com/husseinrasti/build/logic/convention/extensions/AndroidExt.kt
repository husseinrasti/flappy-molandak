package com.husseinrasti.build.logic.convention.extensions

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware


@JvmName("androidApp")
internal fun Project.android(configureApp: Action<BaseAppModuleExtension>): Unit =
    (this as ExtensionAware).extensions.configure("android", configureApp)


@JvmName("androidLib")
internal fun Project.android(configureLib: Action<LibraryExtension>): Unit =
    (this as ExtensionAware).extensions.configure("android", configureLib)
