plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "com.coding.timeline_domain"
}

dependencies {
    implementation(project(Modules.core))
    implementation("com.google.android.gms:play-services-appindexing:9.8.0")
    implementation("com.google.firebase:firebase-firestore-ktx:24.11.0")
}