pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        //for slider
        jcenter()
        maven  ("https://jitpack.io")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        //for slider
        jcenter()
        maven  ("https://jitpack.io")
    }
}

rootProject.name = "FireBaseEcommerce"
include(":app")
