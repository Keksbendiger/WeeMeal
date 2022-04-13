plugins {
    id(Plugins.android_library)
    id(Plugins.kotlin_android)
    id(Plugins.kotlin_kapt)
}

android {
    compileSdk = Config.compile_sdk_version

    defaultConfig {
        minSdk = Config.min_sdk_version
        targetSdk = Config.target_sdk_version

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }
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

    implementation( project(mapOf("path" to ":domain")) )
    implementation( project(mapOf("path" to ":android-core")) )
    implementation( AndroidX.core_ktx)
    implementation( Kotlinx.kotlin_coroutines_android)

    // Room
    kapt( Room.room_compiler )
    implementation( Room.room_runtime)
    implementation( Room.room_ktx )
    androidTestImplementation( Room.room_testing )

    testImplementation( AndroidX.junit)
    androidTestImplementation( AndroidX.test_ext_junit)
    androidTestImplementation( AndroidX.espresso_core)
}