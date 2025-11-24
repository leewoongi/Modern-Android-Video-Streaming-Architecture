pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ModernAndroidVideoStreamingArchitecture"
include(":app")
include(":core")
include(":data:datasource")
include(":data:repository")
include(":domain")
include(":feature:main")
include(":feature:home")
include(":feature:detail")
include(":feature:favorite")
