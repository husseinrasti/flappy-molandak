import com.husseinrasti.build.logic.convention.base.BuildModules

plugins {
    id("build.logic.android.library.compose")
}

android {
    namespace = "com.husseinrasti.game.ui"
}

dependencies {
    implementation(project(BuildModules.COMPONENT))
    implementation(project(BuildModules.Core.MODEL))
    implementation(project(BuildModules.Game.ENGINE))
}