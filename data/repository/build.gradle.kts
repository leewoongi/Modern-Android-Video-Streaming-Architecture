plugins {
    id("modernandroidvideostreamingarchitecture.data")
}

android {
    namespace = "com.woon.modernandroidvideostreamingarchitecture.repository"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data:datasource"))
}