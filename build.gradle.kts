plugins {
    kotlin("jvm") version "1.8.0"
    // Plugin for Dokka - KDoc generating tool
    id("org.jetbrains.dokka") version "1.6.10"
    application
}

group = "ie.setu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
// dependencies for logging
    implementation("io.github.microutils:kotlin-logging:3.0.5")
    implementation("org.slf4j:slf4j-simple:1.7.36")

    //For Streaming to XML and JSON
    implementation("org.codehaus.jettison:jettison:1.5.4")
    implementation("com.thoughtworks.xstream:xstream:1.4.18")

    // For generating a Dokka Site from KDoc
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.6.10")

}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}