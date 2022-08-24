plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = "com.bmc.bancamovilapp"
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName
        vectorDrawables.useSupportLibrary = true
        //multiDexEnabled = true

        testInstrumentationRunner = Config.TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        debug {

        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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

    dynamicFeatures.apply {
        add(":features:logIn")
        add(":features:home")
    }

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

    implementation(project(":navigation"))
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":libraries:common"))
    implementation(project(":libraries:core"))
    implementation(project(":libraries:ui-components"))

    implementation(Libs.kotlin_jdk)

    // This project contains a small subset of OpenJDK libraries simplified for use on older runtimes.
    coreLibraryDesugaring(Libs.desugar_jdk)

    //implementation(Libs.multidex)

    // Android
    implementation(Libs.legacy_support)
    implementation(Libs.appcompat)
    //implementation(Libs.material)
    implementation(Libs.core)
    implementation(Libs.navigation_fragment)
    implementation(Libs.navigation_ui)
    implementation(Libs.constraint_layout)
    implementation(Libs.splash_screen)

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

    // Test
    testImplementation(Libs.junit)
}