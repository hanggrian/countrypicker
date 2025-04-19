pluginManagement.repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
}
dependencyResolutionManagement.repositories {
    mavenCentral()
    google()
}

rootProject.name = "countrypicker"

include("countrypicker", "countrypicker-bottomsheet")
include("sample")
include("website")
