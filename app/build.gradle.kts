import com.google.protobuf.gradle.id

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.services)
    alias(libs.plugins.protobuf.plugin)
    alias(libs.plugins.kotlin.ksp)
    id(
        libs.plugins.kotlin.android
            .get()
            .pluginId,
    )
    id(
        libs.plugins.kotlin.kapt
            .get()
            .pluginId,
    )
    id(
        libs.plugins.safeargs
            .get()
            .pluginId,
    )
    id(
        libs.plugins.dagger.hilt
            .get()
            .pluginId,
    )
    id(
        libs.plugins.ktlint
            .get()
            .pluginId,
    )
}

android {
    namespace = "com.jhtest.storibanktest"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.jhtest.storibanktest"
        minSdk = 27
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE-notice.md"
        }
    }
}

kapt {
    correctErrorTypes = true
}

protobuf {
    protoc {
        artifact =
            libs.protobuf.protoc
                .get()
                .toString()
    }

    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                id("java") {
                    option("lite")
                }
            }
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.core.splash.screen)
    implementation(libs.activity.ktx)

    // Navigation
    implementation(libs.bundles.navigation)

    // Lifecycle
    implementation(libs.bundles.lifecycle)

    // Coroutines
    implementation(libs.coroutine)

    // Networking
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.okhttp.interceptor)

    // CameraX
    implementation(libs.camerax.core)
    implementation(libs.camerax.lifecycle)
    implementation(libs.camerax.view)
    implementation(libs.camerax.extensions)
    implementation(libs.camerax.two)
    implementation(libs.google.guava)

    // Coil
    implementation(libs.coil.compose)

    // Dagger-hilt
    implementation(libs.bundles.hilt)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.compose.ui.tooling)
    kapt(libs.hilt.android.compiler)

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.bundles.accompanist)

    // Mockk
    implementation(libs.mockk.android)
    implementation(libs.mockk.agent)

    // Shimmer
    implementation(libs.shimmer)

    // DataStore
    implementation(libs.datastore.preferences)
    implementation(libs.protobuf)

    // Firebase
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.storage.ktx)

    // Unit test
    testImplementation(libs.junit)
    testImplementation(libs.truth)
    testImplementation(libs.core.testing)
    testImplementation(libs.junit)
    testImplementation(libs.mockk.testing)
    testImplementation(libs.coroutine.test)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.junit.jupiter.params)
    androidTestImplementation(libs.rules)
    androidTestImplementation(libs.hilt.testing)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.mockk.android)
    testRuntimeOnly(libs.junit.jupiter.engine)
    debugImplementation(libs.androidx.ui.test.manifest)
}
