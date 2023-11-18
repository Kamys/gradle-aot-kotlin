plugins {
    kotlin("jvm") version "1.8.0"
    application

    id("org.graalvm.buildtools.native") version "0.9.28"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
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

graalvmNative {
    binaries {
        named("main") {
            imageName.set("my-app")
            mainClass.set("org.example.MainKt")
            buildArgs.add("-O4")
        }
        named("test") {
            buildArgs.add("-O0")
        }
    }
    binaries.all {
        buildArgs.add("--verbose")
    }
}