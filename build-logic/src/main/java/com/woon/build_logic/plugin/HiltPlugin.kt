package com.woon.build_logic.plugin

import com.woon.build_logic.ext.applyPlugin
import com.woon.build_logic.ext.findLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            pluginManager.apply {
                applyPlugin("hilt")
                applyPlugin("ksp")
            }

            dependencies {
                "implementation"(findLibrary("hilt-android"))
                "implementation"(findLibrary("hilt-navigation-compose"))
                add("ksp", findLibrary("hilt-android-compiler"))
            }
        }
    }
}