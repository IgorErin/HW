import org.gradle.nativeplatform.platform.internal.DefaultNativePlatform
import org.jetbrains.compose.compose

plugins {
    kotlin("jvm") version "1.6.10"
    id("org.openjfx.javafxplugin") version "0.0.8"
    id("io.gitlab.arturbosch.detekt") version "1.19.0"
    id("org.jetbrains.compose") version "1.1.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

val jfxVersion = "18.0.1"

fun getJavaFXPlatform(): String {
    val currentOS = DefaultNativePlatform.getCurrentOperatingSystem()
            if (currentOS.isWindows) {
                return "win"
            } else if (currentOS.isLinux) {
                return "linux"
            } else if (currentOS.isMacOsX) {
                return "mac"
            }
    throw IllegalStateException("Unexpected OS: ${currentOS.name}")
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.19.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")

    implementation("org.slf4j:slf4j-simple:1.7.36")

    implementation("org.openjfx:javafx-base:$jfxVersion:${getJavaFXPlatform()}")
    implementation("org.openjfx:javafx-swing:$jfxVersion:${getJavaFXPlatform()}")
    implementation("org.openjfx:javafx-graphics:$jfxVersion:${getJavaFXPlatform()}")

    implementation("org.jetbrains.lets-plot:lets-plot-jfx:2.3.0")
    implementation("org.jetbrains.lets-plot:lets-plot-kotlin-jvm:3.2.0")
    testImplementation(platform("org.junit:junit-bom:5.8.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(kotlin("test"))
}


tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}