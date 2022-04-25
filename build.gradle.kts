import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    kotlin("jvm") version "1.6.20" apply true
    id("test-report-aggregation") apply true
}

allprojects {
    group = "com.github.furrhatted"
    version = "1.0-SNAPSHOT"

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "16"
    }
}

subprojects {
    repositories {
        mavenCentral()
    }

}

dependencies {
    implementation(project(":ui"))
}