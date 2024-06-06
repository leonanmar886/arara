plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("com.google.gms.google-services")
}

android {
  namespace = "com.example.arara"
  compileSdk = 34
  
  buildFeatures {
    compose = true
  }
  
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.11"
  }
  
  defaultConfig {
    applicationId = "com.example.arara"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"
    
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
  
  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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

dependencies {
  
  implementation(libs.cronet.embedded)
  // Compose dependencies
  val composeBom = platform("androidx.compose:compose-bom:2024.03.00")
  implementation(composeBom)
  androidTestImplementation(composeBom)
  implementation("androidx.compose.material3:material3")
  implementation("androidx.compose.ui:ui-tooling-preview")
  debugImplementation("androidx.compose.ui:ui-tooling")
  androidTestImplementation("androidx.compose.ui:ui-test-junit4")
  debugImplementation("androidx.compose.ui:ui-test-manifest")
  implementation("androidx.compose.material:material-icons-core")
  implementation("androidx.compose.material:material-icons-extended")
  implementation("androidx.compose.material3:material3-window-size-class")
  implementation("androidx.activity:activity-compose:1.8.2")
  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
  implementation("androidx.compose.runtime:runtime-livedata")
  implementation("androidx.compose.runtime:runtime-rxjava2")

// AndroidX dependencies
  implementation("androidx.core:core-ktx:1.12.0")
  implementation("androidx.appcompat:appcompat:1.6.1")
  implementation("androidx.constraintlayout:constraintlayout:2.1.4")

// Material Design dependencies
  implementation("com.google.android.material:material:1.11.0")

// Firebase dependencies
  implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
  implementation("com.google.firebase:firebase-analytics")
  implementation("com.google.firebase:firebase-auth-ktx")

// Testing dependencies
  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
  
// Navigation
  val nav_version = "2.7.7"
  implementation("androidx.navigation:navigation-compose:$nav_version")
  implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
  implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
  
  implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

  // Retrofit HTTP Requisition
  implementation ("com.squareup.retrofit2:retrofit:2.9.0")
  implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
}