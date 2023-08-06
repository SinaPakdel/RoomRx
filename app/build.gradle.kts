plugins {
    id("com.android.application")
}

android {
    namespace = "com.sina.roomrx"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.sina.roomrx"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("androidx.room:room-runtime:2.5.2")
    annotationProcessor("androidx.room:room-compiler:2.5.2")
    implementation("androidx.room:room-rxjava3:2.5.2")

    implementation("com.google.dagger:dagger-android:2.20")
    implementation("com.google.dagger:dagger-android-support:2.20")
    annotationProcessor("com.google.dagger:dagger-android-processor:2.20")
    annotationProcessor("com.google.dagger:dagger-compiler:2.46.1")

    implementation("io.reactivex.rxjava3:rxandroid:3.0.0")


}