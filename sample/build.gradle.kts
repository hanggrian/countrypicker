import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val releaseArtifact: String by project

val jdkVersion = JavaLanguageVersion.of(libs.versions.jdk.get())
val jreVersion = JavaLanguageVersion.of(libs.versions.jre.get())

plugins {
    alias(libs.plugins.android.application)
    kotlin("android") version libs.versions.kotlin
    alias(libs.plugins.ktlint)
}

kotlin.jvmToolchain(jdkVersion.asInt())

ktlint.version.set(libs.versions.ktlint.get())

android {
    namespace = "com.example"
    testNamespace = "$namespace.test"
    defaultConfig {
        applicationId = namespace
        multiDexEnabled = true
    }
}

dependencies {
    ktlintRuleset(libs.rulebook.ktlint)

    implementation(project(":$releaseArtifact-bottomsheet"))
    implementation(libs.material)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.multidex)
    implementation(libs.androidx.preference)
    implementation(libs.roundedimageview)
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions
        .jvmTarget
        .set(JvmTarget.fromTarget(JavaVersion.toVersion(jreVersion).toString()))
}
