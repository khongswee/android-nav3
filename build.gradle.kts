// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // This forces the correct version of JavaPoet for all plugins (Hilt, KSP, etc.)
        classpath("com.squareup:javapoet:1.13.0")
    }
}

// ... existing plugins block ...
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}