import com.husseinrasti.build.logic.convention.base.BuildModules

plugins {
    id("build.logic.android.application")
}

android {
    namespace = "com.husseinrasti.game.molandak"
}

dependencies {
    implementation(project(BuildModules.COMPONENT))
    implementation(project(BuildModules.Core.MODEL))
    implementation(project(BuildModules.Game.ENGINE))
    implementation(project(BuildModules.Game.UI))
}