// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
  repositories {
    google()
    mavenCentral()
  }
  dependencies {
    classpath ("com.android.tools.build:gradle:7.0.4");
    classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0")
    classpath ("org.jetbrains.kotlin:kotlin-serialization:1.9.23")
  }
}

plugins {
  id("com.android.application") version "8.2.2" apply false
  id("org.jetbrains.kotlin.android") version "1.9.23" apply false
  id("com.google.gms.google-services") version "4.4.1" apply false
}