plugins {
    id("modernandroidvideostreamingarchitecture.feature")
}

android {
    namespace = "com.woon.modernandroidvideostreamingarchitecture.main"
}

dependencies {
    implementation(project(":feature:home"))
}