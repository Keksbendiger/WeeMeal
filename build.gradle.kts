// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id("com.android.application") version "7.2.0-beta03" apply false
    id("com.android.library") version "7.2.0-beta03" apply false
    id("org.jetbrains.kotlin.android") version "1.6.10" apply false

    id(Plugins.detekt).version(Plugins.detektVersion)
    id(Plugins.dokka).version (Plugins.dokkaVersion)
}

subprojects {
    apply {
        plugin(Plugins.detekt)
        plugin(Plugins.dokka)
        plugin(Plugins.ktlint)
    }

    detekt {
        toolVersion = Plugins.detektVersion
        config = files("../config/detekt/detekt.yml")
        buildUponDefaultConfig = true
    }
}

dependencies {
    detektPlugins( Plugins.detekt_plugin_formatting )
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}
buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")
    }
}

tasks.dokkaHtmlMultiModule.configure {
    outputDirectory.set(buildDir.resolve("dokkaMultiModuleOutput"))
}