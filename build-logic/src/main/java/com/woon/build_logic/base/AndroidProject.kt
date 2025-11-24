package com.woon.build_logic.base

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

internal fun Project.androidProject(
    commonExtension: CommonExtension<*, *, *, *, *, *>
){
    commonExtension.apply {
        compileSdk = 35

        defaultConfig {
            minSdk = 24
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }
}