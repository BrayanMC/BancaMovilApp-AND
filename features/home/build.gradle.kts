plugins {
    id("com.android.dynamic-feature")
    kotlin("android")
    id("androidx.navigation.safeargs.kotlin")
    kotlin("kapt")
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk

        testInstrumentationRunner = Config.TEST_INSTRUMENTATION_RUNNER
    }

//    flavorDimensions.add("version")
//    productFlavors {
//        create("dev") {
//            dimension = "version"
//        }
//
//        create("qa") {
//            dimension = "version"
//        }
//
//        create("prd") {
//            dimension = "version"
//        }
//    }

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "${Config.javaVersion}"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(":app"))
    implementation(project(":navigation"))
    implementation(project(":libraries:core"))
    implementation(project(":libraries:common"))
    implementation(project(":domain"))

    implementation(Libs.kotlin_jdk)

    // Android
    implementation(Libs.legacy_support)
    implementation(Libs.appcompat)
//    implementation(Libs.material)
    implementation(Libs.core)
    implementation(Libs.navigation_fragment)
    implementation(Libs.navigation_ui)
    implementation(Libs.constraint_layout)
    implementation(Libs.swipe_refresh_layout)

    // ViewModel
    implementation(Libs.lifecycle_view_model)
    kapt(Libs.lifecycle_compiler)

    // DI
    implementation(Libs.dagger)
    implementation(Libs.dagger_android_support)
    kapt(Libs.dagger_compiler)
    kapt(Libs.dagger_android_processor)

    // Timber
    implementation(Libs.timber)

    // Testing
    androidTestImplementation(Libs.androidx_junit)
    testImplementation(Libs.junit)
}