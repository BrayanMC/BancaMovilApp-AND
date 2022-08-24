/**
 * To define dependencies
 */
object Libs {
    const val kotlin_jdk = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin_version}"

    val legacy_support = "androidx.legacy:legacy-support-v4:${Versions.legacy_support}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.androidx}"
    const val core = "androidx.core:core-ktx:${Versions.ktx_core}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"

    const val recycler_view = "androidx.recyclerview:recyclerview:${Versions.recycler_view}"
    const val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.androidx_navigation}"
    const val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.androidx_navigation}"
    const val navigation_runtime = "androidx.navigation:navigation-runtime:${Versions.androidx_navigation}"
    val navigation_testing = "androidx.navigation:navigation-testing:${Versions.androidx_navigation}"

    const val lifecycle_compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle_version}"
    const val lifecycle_view_model =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_view_model_version}"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val dagger_android_support = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val dagger_android_processor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    const val junit = "junit:junit:${Versions.junit}"
    val androidx_junit = "androidx.test.ext:junit:${Versions.androidx_junit}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    val retrofit_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"

    val okHttp3 = "com.squareup.okhttp3:okhttp:${Versions.okHttp3_version}"
    val okHttp3Interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp3_version}"

    val gson = "com.google.code.gson:gson:${Versions.gson_version}"

    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlin_coroutines_version}"
    val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlin_coroutines_version}"
    val coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlin_coroutines_version}"

    val javax_annotation_api = "javax.annotation:javax.annotation-api:${Versions.javax_version}"

    const val material = "com.google.android.material:material:${Versions.material_version}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber_version}"

    const val desugar_jdk = "com.android.tools:desugar_jdk_libs:${Versions.desugar_jdk_version}"

    val android_fast_networking = "com.amitshekhar.android:android-networking:${Versions.android_fast_networking_version}"

    val play_core_ktx = "com.google.android.play:core-ktx:${Versions.play_core_ktx_version}"
    val play_core = "com.google.android.play:core:${Versions.play_core_version}"
    val coroutines_play_services = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutines_play_services_version}"

    const val multidex = "androidx.multidex:multidex:${Versions.multidex_version}"

    const val splash_screen = "androidx.core:core-splashscreen:${Versions.splash_screen}"

    const val swipe_refresh_layout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipe_refresh_layout}"
}

object Config {
    const val compileSdk = 32
    const val minSdk = 21
    const val targetSdk = 32
    const val javaVersion = 1.8

    private const val versionMajor = 1
    private const val versionMinor = 0
    private const val versionPatch = 0
    const val versionCode = 1
    const val versionName = "$versionMajor.$versionMinor.$versionPatch"

    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
}

object Versions {
    const val android_gradle_version = "4.0.1"
    const val kotlin_version = "1.5.30"
    const val kotlin_coroutines_version = "1.3.3"
    const val safe_args_version = "2.3.5"

    const val legacy_support = "1.0.0"
    const val constraint_layout = "2.1.0"
    const val androidx = "1.3.1"
    const val ktx_core = "1.8.0"
    const val recycler_view = "1.1.0"
    const val androidx_navigation = "2.5.1"

    const val lifecycle_version = "2.4.0"
    const val lifecycle_view_model_version = "2.4.0"

    const val dagger = "2.40"

    const val junit = "4.12"
    const val androidx_junit = "1.1.3"

    const val fragment_version = "1.3.1"

    const val retrofit_version = "2.6.1"
    const val okHttp3_version = "3.10.0"

    const val gson_version = "2.8.2"

    const val javax_version = "1.3.2"
    const val material_version = "1.1.0"

    const val play_services_version = "4.3.3"

    const val timber_version = "4.7.1"

    const val desugar_jdk_version = "1.0.10"

    const val android_fast_networking_version = "1.0.2"

    const val play_core_ktx_version = "1.8.1"
    const val play_core_version = "1.10.0"

    const val coroutines_play_services_version = "1.4.1"

    const val multidex_version = "2.0.1"

    const val splash_screen = "1.0.0"

    const val swipe_refresh_layout = "1.1.0"
}
