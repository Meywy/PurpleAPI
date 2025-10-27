val mainClass = "dev.meywy.purpleAPI.PurpleAPI"
val minecraftVersion = "1.21.5"
group = "dev.meywy"
version = "1.0"

plugins {
    kotlin("jvm") version "2.2.21"
    id("maven-publish")
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.19" // PaperAPI
    id("com.gradleup.shadow") version "8.3.7" // ShadowJar
    id("xyz.jpenilla.run-paper") version "2.3.1" // Run Paper Server
    id("de.eldoria.plugin-yml.bukkit") version "0.8.0" // plugin.yml
    id("io.github.revxrsal.bukkitkobjects") version "0.0.4" // JavaPlugin KObject
}



repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc-repo"
    }
}

dependencies {
    paperweight.paperDevBundle("${minecraftVersion}-R0.1-SNAPSHOT")

    implementation("gg.flyte:twilight:1.1.22")

    // Lamp Command Framework
    implementation("io.github.revxrsal:lamp.common:4.0.0-beta.17")
    implementation("io.github.revxrsal:lamp.bukkit:4.0.0-beta.17")
    implementation("io.github.revxrsal:lamp.brigadier:4.0.0-beta.17")

}

bukkitKObjects {
    classes.add(mainClass)
}

bukkit {
    main = mainClass
    apiVersion = """(\d+\.\d+)""".toRegex().find(minecraftVersion)?.value ?: minecraftVersion
    author = """^[^.]+\.([^.]+)""".toRegex().find(mainClass)?.groupValues?.get(1)
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        javaParameters = true
    }
}



tasks {
    shadowJar {
        minimize()
    }
    build {
        dependsOn(shadowJar)
    }
    processResources {
        filteringCharset = "UTF-8"
    }
    runServer {
        minecraftVersion(minecraftVersion)
        downloadPlugins {}
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

// Advanced Hotswapping using Jetbrains JRE (must have the project jar set to Jetbrains jar)
tasks.withType(xyz.jpenilla.runtask.task.AbstractRun::class) {
    javaLauncher = javaToolchains.launcherFor {
        vendor = JvmVendorSpec.JETBRAINS
        languageVersion = JavaLanguageVersion.of(21)
    }
    jvmArgs("-XX:+AllowEnhancedClassRedefinition")
}