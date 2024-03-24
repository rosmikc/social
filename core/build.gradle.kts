plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "com.coding.core"
}
dependencies {
    implementation("com.google.firebase:firebase-auth-ktx:22.3.1")
}
