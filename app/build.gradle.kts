plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("com.google.gms.google-services")
  id("kotlinx-serialization")
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
  implementation(libs.firebase.firestore.ktx)
  // Compose dependencies
  val composeBom = platform("androidx.compose:compose-bom:2024.03.00")
  implementation(composeBom)
  androidTestImplementation(composeBom)
  implementation(libs.material3)
  implementation(libs.ui.tooling.preview)
  debugImplementation(libs.ui.tooling)
  androidTestImplementation(libs.ui.test.junit4)
  debugImplementation(libs.ui.test.manifest)
  implementation(libs.material.icons.core)
  implementation(libs.material.icons.extended)
  implementation("androidx.compose.material3:material3-window-size-class")
  implementation(libs.activity.compose)
  implementation(libs.lifecycle.viewmodel.compose)
  implementation(libs.runtime.livedata)
  implementation(libs.runtime.rxjava2)

// AndroidX dependencies
  implementation(libs.core.ktx)
  implementation(libs.appcompat)
  implementation(libs.constraintlayout)

// Material Design dependencies
  implementation(libs.material)

// Firebase dependencies
  implementation(platform(libs.firebase.bom))
  implementation(libs.firebase.analytics)
  implementation(libs.firebase.auth.ktx)
  implementation(libs.play.services.auth)
  implementation(libs.firebase.storage)
  
// Testing dependencies
  testImplementation(libs.junit)
  androidTestImplementation(libs.junit.ext)
  androidTestImplementation(libs.espresso.core)
  
  
//Coil
  implementation(libs.coil.compose)
  
// Navigation
  val nav_version = "2.7.7"
  implementation(libs.nav.version)
  implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
  implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
  
  implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

  // Retrofit HTTP Requisition and Serialization
  implementation (libs.retrofit)
  implementation (libs.converter.gson)
  implementation(libs.kotlinx.serialization.json)
}