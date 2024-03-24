plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "com.coding.timeline_data"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.timelineDomain))

    implementation(Retrofit.okHttp)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.moshiConverter)
    implementation(Retrofit.okHttpLoggingInterceptor)
    implementation("com.google.firebase:firebase-common-ktx:20.4.3")
    implementation("com.google.firebase:firebase-firestore")

    "kapt"(Room.roomCompiler)
    implementation(Room.roomKtx)
    implementation(Room.roomRuntime)
}