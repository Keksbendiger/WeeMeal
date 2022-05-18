import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

/**
 * Adds a dependency to the `releaseImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.releaseImplementation(dependencyNotation: Any): Dependency? =
    add("releaseImplementation", dependencyNotation)

/**
 * Adds a dependency to the `debugImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.debugImplementation(dependencyNotation: Any): Dependency? =
    add("debugImplementation", dependencyNotation)

/**
 * Adds a dependency to the `implementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)


/**
 * Adds a dependency to the `implementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.annotationProcessor(dependencyNotation: Any): Dependency? =
    add("annotationProcessor", dependencyNotation)


/**
 * Adds a dependency to the `api` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)

/**
 * Adds a dependency to the `kapt` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

/**
 * Adds a dependency to the `testImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)


/**
 * Adds a dependency to the `androidTestImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

/**
 * Adds a dependency to the `ksp` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.ksp(dependencyNotation: Any): Dependency? =
    add("ksp", dependencyNotation)


fun DependencyHandler.addAndroidXDependencies() {
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifecycleKtx)
    implementation(AndroidX.espressoCore)
    implementation(AndroidX.testExtJunit)
    implementation(AndroidX.junit)
}


fun DependencyHandler.addComposeDependencies() {

    implementation(Compose.ui)
    implementation(Compose.material)
    implementation(Compose.toolingPreview)
    implementation(Compose.activityCompose)
    implementation(Compose.composeNavigation)
    implementation(Compose.runtimeLiveData)

    // Test
    androidTestImplementation(Compose.junit)

    //DebugDependencies
    debugImplementation(Compose.uiTooling)
}

fun DependencyHandler.addKoinDependencies() {
    implementation(Koin.core)
    implementation(Koin.android)
    implementation(Koin.androidCompose)
    implementation(Koin.androidNavigation)

    //Test
    testImplementation(Koin.test)
}

fun DependencyHandler.addKontlinxDependencies() {
    implementation(Kotlinx.kotlinCoroutinesCore)
    implementation(Kotlinx.kotlinCoroutinesAndroid)
}

fun DependencyHandler.addKtorDependencies() {
    implementation(Ktor.core)
    implementation(Ktor.clientSerialization)
    implementation(Ktor.android)
}

fun DependencyHandler.addRoomDependencies() {
    implementation(Room.roomRuntime)
//    implementation(Room.roomCompiler)
    annotationProcessor(Room.roomCompiler)
    implementation(Room.roomKtx)
//    implementation(Room.roomCoroutine)

    //Test
    testImplementation(Room.roomTesting)
}

fun DependencyHandler.addGoogleDependencies() {
    implementation(Google.material)
}

fun DependencyHandler.addTimberDependencies() {
    implementation(Timber.timber)
}


fun DependencyHandler.addModuleDependencies() {
    implementation(project(Modules.domain))
    implementation(project(Modules.common))
//    implementation(project(Modules.model))
    implementation(project(Modules.local))
    implementation(project(Modules.remote))
    implementation(project(Modules.repository))

    implementation(project(Modules.featureTest))
}



// Modules
val DependencyHandler.FEATURE_TEST
    get() =  implementation(project(Modules.featureTest))

val DependencyHandler.LOCAL
    get() =  implementation(project(Modules.local))

val DependencyHandler.REMOTE
    get() =  implementation(project(Modules.remote))

val DependencyHandler.REPOSITORY
    get() =  implementation(project(Modules.repository))

val DependencyHandler.DOMAIN
    get() =  implementation(project(Modules.domain))

val DependencyHandler.COMMON
    get() =  implementation(project(Modules.common))


