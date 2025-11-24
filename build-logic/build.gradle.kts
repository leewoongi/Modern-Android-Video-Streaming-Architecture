import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.hilt.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("applicationPlugin") {
            id = "modernandroidvideostreamingarchitecture.application"
            implementationClass = "com.woon.build_logic.plugin.ApplicationPlugin"
        }
        register("corePlugin") {
            id = "modernandroidvideostreamingarchitecture.core"
            implementationClass = "com.woon.build_logic.plugin.CorePlugin"
        }
        register("featurePlugin") {
            id = "modernandroidvideostreamingarchitecture.feature"
            implementationClass = "com.woon.build_logic.plugin.FeaturePlugin"
        }
        register("hiltPlugin") {
            id = "modernandroidvideostreamingarchitecture.hilt"
            implementationClass = "com.woon.build_logic.plugin.HiltPlugin"
        }
        register("dataPlugin") {
            id = "modernandroidvideostreamingarchitecture.data"
            implementationClass = "com.woon.build_logic.plugin.DataPlugin"
        }
        register("domainPlugin") {
            id = "modernandroidvideostreamingarchitecture.domain"
            implementationClass = "com.woon.build_logic.plugin.DomainPlugin"
        }
        register("roomPlugin") {
            id = "modernandroidvideostreamingarchitecture.room"
            implementationClass = "com.woon.build_logic.plugin.RoomPlugin"
        }
    }
}
