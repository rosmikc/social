plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "com.coding.onboarding_data"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.onboardingDomain))
    implementation("com.google.firebase:firebase-auth-ktx:22.3.1")
}