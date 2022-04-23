pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = ("weemeal")

include(
    ":app"
)

include(":common")
include(":domain")
include(":data:model")
include(":data:local")
include(":data:remote")
include(":data:repository")
include(":feature:test")
