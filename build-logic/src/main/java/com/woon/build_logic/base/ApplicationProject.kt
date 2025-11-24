package com.woon.build_logic.base

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Project

internal fun Project.applicationProject(
    applicationExtension: ApplicationExtension
){
    applicationExtension.apply {
        namespace = "com.woon.modernandroidvideostreamingarchitecture"
        compileSdk = 35

        defaultConfig {
            applicationId = "com.woon.modernandroidvideostreamingarchitecture"
            minSdk = 24
            targetSdk = 35
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
    }
}