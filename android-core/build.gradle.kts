plugins {
    id(Plugins.android_library)
    id(Plugins.kotlin_android)
}

android {
    compileSdk = Config.compile_sdk_version

    defaultConfig {
        minSdk = Config.min_sdk_version
        targetSdk = Config.target_sdk_version

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = Config.jvm_target
    }
}

dependencies {

    implementation(project(mapOf("path" to ":domain")))
    implementation(AndroidX.core_ktx)

    implementation(Timber.timber)

    //implementation("androidx.appcompat:appcompat:1.4.1")

    testImplementation(AndroidX.junit) //TODO: was jnuit:junit is adroidX:junit
    androidTestImplementation(AndroidX.test_ext_junit)
    androidTestImplementation(AndroidX.espresso_core)
}