package com.woon.build_logic.base

import com.android.build.api.dsl.CommonExtension
import com.woon.build_logic.ext.findLibrary
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.glideProject(
    commonExtension: CommonExtension<*, *, *, *, *, *>
){
    commonExtension.apply {
        dependencies {
            "implementation"(findLibrary("glide"))
        }
    }
}