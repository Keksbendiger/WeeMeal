plugins {
    id(Plugins.android_app)
    id(Plugins.kotlin_android)
}

android {
    compileSdk = Config.compile_sdk_version
    buildToolsVersion = Config.build_tools_version

    defaultConfig {
        applicationId = Config.application_id
        minSdk = Config.min_sdk_version
        targetSdk = Config.target_sdk_version
        versionCode = Config.version_code
        versionName = Config.version_name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = Config.jvm_target
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.composeVersion
    }
    packagingOptions {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
}

dependencies {

    implementation(project(mapOf("path" to ":domain")))
    implementation(project(mapOf("path" to ":android-core")))
    implementation(project(mapOf("path" to ":data")))

    implementation(AndroidX.core_ktx)
    implementation(AndroidX.lifecycle_ktx)
    implementation(Compose.ui)
    implementation(Compose.material)
    implementation(Compose.tooling_preview)
    implementation(Compose.activity_compose)
    implementation(Compose.compose_navigation)


    implementation(Koin.core)
    implementation(Koin.android)
    implementation(Koin.android_compose)
    implementation(Koin.android_navigation)

    testImplementation(AndroidX.junit) // TODO: was junit is androidx:junit
    androidTestImplementation(Compose.junit)
    androidTestImplementation(AndroidX.espresso_core)
    androidTestImplementation(AndroidX.test_ext_junit)
    androidTestImplementation(Koin.test)

    debugImplementation(Compose.ui_tooling)
}