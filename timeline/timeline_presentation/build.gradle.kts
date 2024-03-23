plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/compose-module.gradle")

android {
    namespace = "com.coding.timeline_presentation"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.timelineDomain))

    implementation(Coil.coilCompose)
}