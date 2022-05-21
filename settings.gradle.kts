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
        maven { url = uri("https://jitpack.io") }
    }
}


rootProject.name = ("weemeal")

include(
    ":app"
)

include(":common")
include(":domain")
include(":data:local")
include(":data:remote")
include(":data:repository")
include(":feature:test")
include(":usecases")
include(":mocks")
