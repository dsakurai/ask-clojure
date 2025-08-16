plugins {
    kotlin("jvm") version "2.1.0"
    application
    id("org.openjfx.javafxplugin") version "0.1.0" // Use the latest stable version
    id("application")
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
//     maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }
//     maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
//     maven { url = uri("https://repo.openjfx.io") }
}

javafx {
    modules("javafx.controls", "javafx.fxml", "javafx.swing")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-javafx:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
}

application {
    mainClass.set("App")
}