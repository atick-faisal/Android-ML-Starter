
// ... Project Plugins

object BuildPlugins {
    val android by lazy { "com.android.tools.build:gradle:${Versions.GRADLE_PLUGIN}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}" }
    val hilt by lazy { "com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}" }
    val navigation by lazy { "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.NAVIGATION}" }
}