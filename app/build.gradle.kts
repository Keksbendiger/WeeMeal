//import extensions.addCommonDependencies

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
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1,gradle-plugins}")
    }
}

dependencies {
    addAndroidXDependencies()
    addComposeDependencies()
    addKoinDependencies()
    addModuleDependencies()
}