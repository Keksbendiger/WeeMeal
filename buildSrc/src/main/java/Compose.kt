object Compose {
    const val composeVersion = "1.1.1" //TODO: why not 1.1?
    private const val composeNavigationVersion = "2.4.1"
    private const val activityComposeVersion = "1.4.0"

    const val ui = "androidx.compose.ui:ui:${composeVersion}"
    const val material = "androidx.compose.material:material:${composeVersion}"
    const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:${composeVersion}"
    const val activityCompose = "androidx.activity:activity-compose:${activityComposeVersion}"
    const val composeNavigation = "androidx.navigation:navigation-compose:${composeNavigationVersion}"
    const val runtimeLiveData = "androidx.compose.runtime:runtime-livedata:${composeVersion}"

    // Test
    const val junit = "androidx.compose.ui:ui-test-junit4:${composeVersion}"

    //DebugDependencies
    const val uiTooling = "androidx.compose.ui:ui-tooling:${composeVersion}"

}