object Compose {
    const val composeVersion = "1.0.5" //TODO: why not 1.1?
    private const val composeNavigationVersion = "2.4.1"
    private const val activityComposeVersion = "1.4.0"

    const val ui = "androidx.compose.ui:ui:${composeVersion}"
    const val material = "androidx.compose.material:material:${composeVersion}"
    const val tooling_preview = "androidx.compose.ui:ui-tooling-preview:${composeVersion}"
    const val activity_compose = "androidx.activity:activity-compose:${activityComposeVersion}"
    const val compose_navigation = "androidx.navigation:navigation-compose:${composeNavigationVersion}"

    // Test
    const val junit = "androidx.compose.ui:ui-test-junit4:${composeVersion}"

    //DebugDependencies
    const val ui_tooling = "androidx.compose.ui:ui-tooling:${composeVersion}"

}