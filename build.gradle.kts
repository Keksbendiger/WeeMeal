// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id(Plugins.detekt).version(Plugins.detektVersion)
    id(Plugins.dokka).version(Plugins.dokkaVersion)
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
        autoCorrect = true
    }
}

dependencies {
    detektPlugins(Plugins.detekt_plugin_formatting)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
buildscript {
    dependencies {
        classpath(Build.buildTools)
        classpath(Build.kotlinGradlePlugin)
    }
}

tasks.dokkaHtmlMultiModule.configure {
    outputDirectory.set(buildDir.resolve("dokkaMultiModuleOutput"))
}
