plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose") // ✅ Required for Kotlin 2.0+ Compose support
}

android {
    namespace = "com.example.application.nueropro"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.application.nueropro"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3" // Ensure this is the latest compatible version
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Core Android dependencies
    implementation("androidx.core:core-ktx:1.12.0")
    implementation ("androidx.activity:activity-compose:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.1")

    // Jetpack Compose UI
    implementation("androidx.compose.ui:ui:1.5.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.3")
    implementation("androidx.compose.material3:material3:1.1.2")

    implementation ("androidx.compose.material3:material3:1.0.0")
    implementation ("androidx.compose.ui:ui:1.2.0")
    implementation ("androidx.navigation:navigation-compose:2.4.0")
    implementation ("androidx.compose.runtime:runtime:1.2.0")

    implementation ("androidx.compose.material3:material3:1.0.0-beta01") // Make sure this is added
    implementation ("androidx.compose.ui:ui:1.3.0")
    implementation ("androidx.navigation:navigation-compose:2.4.0")

    // ViewModel & LiveData for Jetpack Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.3")

    // Bluetooth & Permissions (If required)
    implementation("androidx.core:core-ktx:1.12.0")

    // Compose Compiler Plugin (Mandatory for Kotlin 2.0+)
    implementation("androidx.compose.compiler:compiler:1.5.3")
    implementation(libs.androidx.navigation.runtime.android)
    implementation(libs.androidx.core.splashscreen)
    implementation ("androidx.compose.material3:material3:1.0.0")

    // Testing Dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("androidx.activity:activity-compose:1.9.0")
    implementation ("androidx.compose.ui:ui:1.6.7")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.6.7")
    implementation ("androidx.compose.material3:material3:1.2.1")

    // Lifecycle & ViewModel
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.9.0-alpha12")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.0-alpha12")
    implementation ("androidx.compose.material:material-icons-extended:1.5.4")
    implementation ("androidx.compose.material3:material3:1.2.0")
    implementation ("androidx.compose.material:material-icons-extended:1.5.4")

    // Navigation Component for Compose
    implementation ("androidx.navigation:navigation-compose:2.7.6")

    // Jetpack Compose UI Testing
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.3")
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.3")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation( "androidx.activity:activity-compose:1.9.0")
    implementation ("androidx.compose.ui:ui:1.6.7")
    implementation ("androidx.compose.material3:material3:1.2.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.9.0-alpha12")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.0-alpha12")
    implementation ("com.google.accompanist:accompanist-permissions:0.28.0")
    implementation ("androidx.compose.material3:material3:1.0.0")
    implementation ("com.google.accompanist:accompanist-permissions:<version>")
    implementation ("androidx.compose.ui:ui:1.6.0")
    implementation ("androidx.compose.material:material:1.6.0")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.6.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation ("androidx.activity:activity-compose:1.8.2")
}


