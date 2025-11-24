plugins {
    id("modernandroidvideostreamingarchitecture.data")
}

android {
    namespace = "com.woon.modernandroidvideostreamingarchitecture.datasource"
}

dependencies {
    implementation(project(":domain"))
}