plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.example.orders.data"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = 28
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

}

dependencies {
    implementation(projects.core.network)
    implementation(projects.orders.domain)
    implementation(libs.androidx.core.ktx)
}