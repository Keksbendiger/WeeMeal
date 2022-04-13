object Build {
    private const val gradleBuildTools = "7.0.0"
//    private const val gradleBuildTools = "4.2.1" // TODO: Does this work?
    private const val kotlinVersion = "1.5.31"

    const val buildTools = "com.android.tools.build:gradle:${gradleBuildTools}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}"
}