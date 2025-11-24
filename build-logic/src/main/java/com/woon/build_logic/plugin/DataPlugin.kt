package com.woon.build_logic.plugin

import com.android.build.gradle.LibraryExtension
import com.woon.build_logic.base.androidProject
import com.woon.build_logic.base.junitProject
import com.woon.build_logic.base.kotlinProject
import com.woon.build_logic.ext.applyPlugin
import com.woon.build_logic.ext.findLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import java.util.Properties

class DataPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                applyPlugin("android-library")
                applyPlugin("kotlin-android")
                apply("modernandroidvideostreamingarchitecture.hilt")
                apply("modernandroidvideostreamingarchitecture.room")
            }

            extensions.configure<LibraryExtension> {
                val properties = Properties()
                val localPropertiesFile = project.rootProject.file("local.properties")
                if (localPropertiesFile.exists()) {
                    properties.load(localPropertiesFile.inputStream())
                }

                buildFeatures {
                    buildConfig = true
                }

                androidProject(this)
                kotlinProject(this)
                junitProject(this)
            }

            dependencies {
                "implementation"(findLibrary("retrofit"))
                "implementation"(findLibrary("retrofit-converter-gson"))
                "implementation"(findLibrary("okhttp3"))
                "implementation"(findLibrary("okhttp3-logging-interceptor"))
                "implementation"(findLibrary("paging-runtime"))
                add("implementation", project(":domain"))
            }
        }
    }
}