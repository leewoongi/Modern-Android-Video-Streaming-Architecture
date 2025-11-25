plugins {
    id("modernandroidvideostreamingarchitecture.feature")
}

android {
    namespace = "com.woon.modernandroidvideostreamingarchitecture.detail"
}

dependencies {
    implementation(project(":player"))
}