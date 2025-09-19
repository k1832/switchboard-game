plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.switchboardgame"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.switchboardgame"
        minSdk = 29 // Kept from original app/build.gradle.kts
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true // Added from old root build.gradle.kts
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false // Kept from original app/build.gradle.kts
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11 // Kept from original app/build.gradle.kts
        targetCompatibility = JavaVersion.VERSION_11 // Kept from original app/build.gradle.kts
    }
    kotlinOptions {
        jvmTarget = "11" // Kept from original app/build.gradle.kts
    }
    buildFeatures {
        compose = false
        dataBinding = true // Ensured this is true
    }
    // This block copies your web app files into the Android assets folder before building.
    sourceSets {
        getByName("main") {
            assets.srcDirs("../../web") // Corrected path
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.appcompat) // Use AppCompat for compatibility
    implementation(libs.androidx.constraintlayout)
    implementation(libs.google.android.material)
    implementation(libs.androidx.webkit) // Added
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}

