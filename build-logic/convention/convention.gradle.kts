plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.ksp.gradlePlugin)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

gradlePlugin {
    plugins {
        create("buildLogicAndroidApplication") {
            id = "build.logic.android.application"
            implementationClass = "com.husseinrasti.build.logic.convention.plugins.AndroidApplicationConventionPlugin"
        }
        create("buildLogicAndroidLibrary") {
            id = "build.logic.android.library"
            implementationClass = "com.husseinrasti.build.logic.convention.plugins.AndroidLibraryConventionPlugin"
        }
        create("buildLogicLibraryCompose") {
            id = "build.logic.android.library.compose"
            implementationClass = "com.husseinrasti.build.logic.convention.plugins.AndroidLibraryComposeConventionPlugin"
        }
        create("buildLogicLibraryComponent") {
            id = "build.logic.android.library.component"
            implementationClass = "com.husseinrasti.build.logic.convention.plugins.AndroidComponentConventionPlugin"
        }
        create("buildLogicLibraryHilt") {
            id = "build.logic.android.hilt"
            implementationClass = "com.husseinrasti.build.logic.convention.plugins.AndroidHiltConventionPlugin"
        }
    }
}