import com.android.build.gradle.AppPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

val releaseGroup: String by project
val releaseVersion: String by project

val jdkVersion = JavaLanguageVersion.of(libs.versions.jdk.get())
val jreVersion = JavaLanguageVersion.of(libs.versions.jre.get())

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.maven.publish) apply false
}

allprojects {
    group = releaseGroup
    version = releaseVersion
}

subprojects {
    plugins.withType<LibraryPlugin>().configureEach {
        modify(the<LibraryExtension>())
    }
    plugins.withType<AppPlugin>().configureEach {
        modify(the<BaseAppModuleExtension>())
    }
}

fun modify(extension: BaseExtension) {
    extension.setCompileSdkVersion(libs.versions.sdk.target.get().toInt())
    extension.defaultConfig {
        minSdk = libs.versions.sdk.min.get().toInt()
        targetSdk = libs.versions.sdk.target.get().toInt()
        version = releaseVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    extension.compileOptions {
        sourceCompatibility = JavaVersion.toVersion(jreVersion)
        targetCompatibility = JavaVersion.toVersion(jreVersion)
    }
}
