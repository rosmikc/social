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
    implementation("com.google.firebase:firebase-common-ktx:20.4.3")
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-storage-ktx:19.1.1")

    implementation(Coil.coilCompose)
}