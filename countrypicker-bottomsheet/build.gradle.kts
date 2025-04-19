val releaseGroup: String by project
val releaseArtifact: String by project

plugins {
    kotlin("android") version libs.versions.kotlin.get()
    alias(libs.plugins.android.library)
    checkstyle
    jacoco
    alias(libs.plugins.maven.publish)
}

android {
    namespace = "$releaseGroup.bottomsheet"
    testNamespace = "$namespace.test"
    buildFeatures.buildConfig = false
    testOptions.unitTests.isIncludeAndroidResources = true
}

dependencies {
    checkstyle(libs.rulebook.checkstyle)

    api(project(":$releaseArtifact"))

    implementation(libs.material)

    testImplementation(libs.bundles.androidx.test)
}
