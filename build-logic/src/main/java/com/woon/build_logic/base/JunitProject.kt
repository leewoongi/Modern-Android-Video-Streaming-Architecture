package com.woon.build_logic.base

import com.android.build.api.dsl.CommonExtension
import com.woon.build_logic.ext.findLibrary
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.junitProject(
    commonExtension : CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.apply {
        dependencies {
            "implementation"(findLibrary("junit"))
            "implementation"(findLibrary("androidx-junit"))
            "implementation"(findLibrary("androidx-espresso-core"))
        }
    }
}