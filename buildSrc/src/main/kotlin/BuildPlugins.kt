
// ... Project Plugins

object BuildPlugins {
    val android by lazy { "com.android.tools.build:gradle:${Versions.GRADLE_PLUGIN}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}" }
}