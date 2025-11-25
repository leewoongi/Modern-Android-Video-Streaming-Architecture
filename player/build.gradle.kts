plugins {
    id("modernandroidvideostreamingarchitecture.core")
}

android {
    namespace = "com.woon.modernandroidvideostreamingarchitecture.player"
}

dependencies {
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.ui)
    implementation(libs.androidx.media3.common)
}
