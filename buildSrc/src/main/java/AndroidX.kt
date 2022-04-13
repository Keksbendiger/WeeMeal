object AndroidX {
    const val coreKtxVersion = "1.7.0"
    private const val lifecycleKtxVersion = "2.4.0"
    private const val junitVersion = "1.1.3"
    private const val espressoCoreVersion = "3.4.0"
    const val testExtJunitVersion = "1.1.3"

    const val core_ktx = "androidx.core:core-ktx:${coreKtxVersion}"
    const val lifecycle_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:${lifecycleKtxVersion}"

    // Test
    const val espresso_core = "androidx.test.espresso:espresso-core:${espressoCoreVersion}"

    // Added for Android 12 workaround (exported = true) TODO: Remove as soon as possible
    const val test_ext_junit = "androidx.test.ext:junit-ktx:${testExtJunitVersion}"
    const val junit = "androidx.test.ext:junit:${junitVersion}"


}
