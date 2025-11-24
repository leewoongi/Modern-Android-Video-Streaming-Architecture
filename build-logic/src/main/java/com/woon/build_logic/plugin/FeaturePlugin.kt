package com.woon.build_logic.plugin

import com.android.build.gradle.LibraryExtension
import com.woon.build_logic.base.androidProject
import com.woon.build_logic.base.composeProject
import com.woon.build_logic.base.glideProject
import com.woon.build_logic.base.junitProject
import com.woon.build_logic.base.kotlinProject
import com.woon.build_logic.ext.applyPlugin
import com.woon.build_logic.ext.findLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class FeaturePlugin : Plugin<Project>{
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                applyPlugin("android-library")
                applyPlugin("kotlin-android")
                applyPlugin("kotlin-compose")
                apply("modernandroidvideostreamingarchitecture.hilt")
            }

            extensions.configure<LibraryExtension> {
                androidProject(this)
                kotlinProject(this)
                junitProject(this)
                composeProject(this)
                glideProject(this)
            }

            dependencies {
                add("implementation", project(":domain"))
                add("implementation", project(":core"))
            }
        }
    }
}