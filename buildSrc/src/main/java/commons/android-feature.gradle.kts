//package commons
//
//import Modules
//import androidTestImplementation
//import implementation
//import org.gradle.kotlin.dsl.dependencies
//import org.gradle.kotlin.dsl.project
//import testImplementation
//testImplementation
//plugins {
//    id("com.android.library")
//    id("kotlin-android")
//    id("kotlin-parcelize")
//}
//
//android {
//
//    ndkVersion = Version.ndkVersion
//
//    compileSdkVersion(Version.compileSdk)
//
//    defaultConfig {
//        minSdkVersion(Version.minSdk)
//        targetSdkVersion(Version.targetSdk)
//        versionCode = Version.versionCode
//        versionName = Version.versionName
//    }
//
//    buildTypes {
//        getByName("release") {
//            isMinifyEnabled = true
//            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
//        }
//    }
//
//    compileOptions {
//        sourceCompatibility(JavaVersion.VERSION_1_8)
//        targetCompatibility(JavaVersion.VERSION_1_8)
//    }
//
//
//    kotlinOptions {
//        jvmTarget = "1.8"
//    }
//
//    lintOptions {
//        lintConfig = file("$rootDir/lint.xml")
//    }
//
//    buildFeatures {
//        viewBinding = true
//    }
//}
//
//
//dependencies {
//    implementation(project(Modules.repository))
//    implementation(project(Modules.interactor))
//    implementation(project(Modules.model))
//    implementation(project(Modules.common))
//
//    implementation(Dependencies.kotlinStandardLib)
//    implementation(Dependencies.kotlinReflect)
//
//    implementation(Dependencies.koin)
//    implementation(Dependencies.koinViewModel)
//    implementation(Dependencies.lifecycleExtension)
//    implementation(Dependencies.lifecycleViewmodel)
//    implementation(Dependencies.lifecycleViewmodelSavedState)
//    implementation(Dependencies.constraintLayout)
//    implementation(Dependencies.legacySupport)
//    implementation(Dependencies.material)
//    implementation(Dependencies.lottie)
//    implementation(Dependencies.glide)
//    implementation(Dependencies.flowlayout)
//    implementation(Dependencies.expandableTextView)
//    implementation(Dependencies.permission)
//    implementation(Dependencies.flexBoxLayout)
//
//
//    implementation(Dependencies.appcompat)
//    implementation(Dependencies.coreKtx)
//    implementation(Dependencies.fragmentKtx)
//
//    implementation(Dependencies.coroutinesCore)
//    implementation(Dependencies.coroutinesAndroid)
//    implementation(Dependencies.lifecycleLivedata)
//
//    testImplementation(project(Modules.mockFactory))
//    testImplementation(TestDependencies.core)
//    testImplementation(TestDependencies.junit)
//    testImplementation(TestDependencies.androidJUnit)
//    testImplementation(TestDependencies.koinTesting)
//    testImplementation(TestDependencies.archCoreTesting)
//    testImplementation(TestDependencies.coroutinesTest)
//
//    androidTestImplementation(TestDependencies.coroutinesTest)
//}
//
//// tasks {
////     named<Test>("test") {
////         testLogging {
////             events("passed", "skipped", "failed")
////             showStackTraces = true
////             exceptionFormat = TestExceptionFormat.FULL
////         }
////     }
//// }
//
//tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).all {
//    kotlinOptions.freeCompilerArgs += "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi"
//    kotlinOptions.freeCompilerArgs += "-Xopt-in=org.koin.core.component.KoinApiExtension"
//}