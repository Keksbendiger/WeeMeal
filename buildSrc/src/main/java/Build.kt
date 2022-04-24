object Build {
    private const val gradleBuildToolsVersion = "7.0.0"
//    private const val gradleBuildToolsVersion = "4.2.1" // TODO: Does this work?
    private const val kotlinVersion = "1.6.10"

    const val buildTools = "com.android.tools.build:gradle:${gradleBuildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}"
}