import com.husseinrasti.build.logic.convention.base.BuildModules

plugins {
    id("build.logic.android.library.compose")
}

android {
    namespace = "com.husseinrasti.game.engine"
}

dependencies {
    implementation(project(BuildModules.Core.MODEL))
}