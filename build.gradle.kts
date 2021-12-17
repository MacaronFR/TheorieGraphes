import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URL

plugins {
    kotlin("jvm") version "1.6.10"
    id("org.jetbrains.dokka") version "1.6.0"
    application
}

group = "fr.macaron.dev"
version = "v0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    dokkaHtmlPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:1.6.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.dokkaHtml.configure{
    outputDirectory.set(projectDir.resolve("doc"))
    moduleName.set("Tree")
    dokkaSourceSets{
        named("main"){
            sourceLink{
                localDirectory.set(file("src/main/kotlin"))
                remoteUrl.set(URL("https://github.com/MacaronFR/Tree/tree/master/src/main/kotlin"))
            }
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

application {
    mainClass.set("MainKt")
}