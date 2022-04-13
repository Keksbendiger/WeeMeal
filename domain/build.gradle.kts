plugins {
    id(Plugins.java_library)
    id(Plugins.kotlin_jvm)
}

dependencies {
    implementation(Kotlinx.kotlinCoroutinesCore)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}