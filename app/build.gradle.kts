plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt)
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.kbcoding.featuremodules"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kbcoding.featuremodules"
        minSdk = 24
        targetSdk = 34
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

kapt {
    correctErrorTypes = true
}

hilt {
    enableAggregatingTask = true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.material)

    // Hilt
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    // Navigation
    implementation(libs.navigation.fragment)

    implementation(projects.core.commonImpl)
    implementation(projects.core.presentation)
    implementation(projects.wiring)
    implementation(projects.navigation)
    implementation(projects.features.signIn)
    implementation(projects.features.signUp)
    implementation(projects.data)
    implementation(projects.features.catalog)
    implementation(projects.features.cart)
    implementation(projects.features.profile)
    implementation(projects.features.orders)
//    implementation(libs.androidx.activity)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
}