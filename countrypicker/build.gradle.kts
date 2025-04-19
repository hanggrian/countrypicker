import com.vanniktech.maven.publish.AndroidSingleVariantLibrary
import com.vanniktech.maven.publish.SonatypeHost

val developerId: String by project
val developerName: String by project
val developerUrl: String by project
val releaseGroup: String by project
val releaseArtifact: String by project
val releaseVersion: String by project
val releaseDescription: String by project
val releaseUrl: String by project

plugins {
    alias(libs.plugins.android.library)
    checkstyle
    jacoco
    alias(libs.plugins.maven.publish)
}

val jdkVersion = JavaLanguageVersion.of(libs.versions.jdk.get())
val jreVersion = JavaLanguageVersion.of(libs.versions.jre.get())

java.toolchain.languageVersion.set(jdkVersion)

android {
    namespace = "$releaseGroup.$releaseArtifact"
    testNamespace = "$namespace.test"
    buildFeatures.buildConfig = false
    testOptions.unitTests.isIncludeAndroidResources = true
}

checkstyle.toolVersion = libs.versions.checkstyle.get()

dependencies {
    checkstyle(libs.rulebook.checkstyle)

    implementation(libs.material)
    implementation(libs.androidx.cardview)
    implementation(libs.androidx.recyclerview)
    implementation(libs.fastscroll)

    testImplementation(libs.bundles.androidx.test)
}

mavenPublishing {
    configure(AndroidSingleVariantLibrary())
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
    pom {
        name.set(project.name)
        description.set(releaseDescription)
        url.set(releaseUrl)
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set(developerId)
                name.set(developerName)
                url.set(developerUrl)
            }
        }
        scm {
            url.set(releaseUrl)
            connection.set("scm:git:https://github.com/$developerId/$releaseArtifact.git")
            developerConnection
                .set("scm:git:ssh://git@github.com/$developerId/$releaseArtifact.git")
        }
    }
}

tasks {
    register<Checkstyle>("checkstyleAndroid") {
        group = LifecycleBasePlugin.VERIFICATION_GROUP
        description = "Generate Android lint report"

        source("src")
        include("**/*.java")
        exclude("**/gen/**", "**/R.java")
        classpath = files()
    }

    withType<Test>().configureEach {
        configure<JacocoTaskExtension> {
            isIncludeNoLocationClasses = true
            excludes = listOf("jdk.internal.*")
        }
    }
    register<JacocoReport>("jacocoAndroid") {
        group = "Reporting"
        description = "Generate Android test coverage"

        dependsOn("testDebugUnitTest", "connectedDebugAndroidTest")
        mustRunAfter("test")
        reports {
            xml.required.set(true)
            html.required.set(true)
        }
        sourceDirectories.setFrom(layout.projectDirectory.dir("src/main/java"))
        classDirectories.setFrom(
            files(
                fileTree(layout.buildDirectory.dir("intermediates/javac/")) {
                    exclude(
                        "**/R.class",
                        "**/R\$*.class",
                        "**/BuildConfig.*",
                        "**/Manifest*.*",
                        "**/*Test*.*",
                        "**/*Args.*",
                        "**/*Directions.*",
                    )
                },
            ),
        )
        executionData.setFrom(
            files(
                fileTree(layout.buildDirectory) {
                    include("**/*.exec", "**/*.ec")
                }
            ),
        )
    }
}
