buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(BuildPlugins.android)
        classpath(BuildPlugins.kotlin)
        // ... Hilt
        classpath(BuildPlugins.hilt)
        // ... Navigation Safe Args
        classpath(BuildPlugins.navigation)
    }
}

task("clean", Delete::class) {
    delete(rootProject.buildDir)
}