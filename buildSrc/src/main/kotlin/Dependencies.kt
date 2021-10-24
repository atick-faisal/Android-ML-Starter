// ... Project dependencies

object Dependencies {

    // ... Core
    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.CORE_KTX}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.APP_COMPAT}" }
    val activityKtx by lazy { "androidx.activity:activity-ktx:${Versions.ACTIVITY_KTX}" }
    val lifecycleKtx by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE_KTX}" }

    // ... Navigation
    val navFragmentKtx by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}" }
    val navUiKtx by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}" }

    // ... Jetpack Compose
    val composeUi by lazy { "androidx.compose.ui:ui:${Versions.COMPOSE}" }
    val composeFoundation by lazy { "androidx.compose.foundation:foundation:${Versions.COMPOSE}" }
    val composeActivity by lazy { "androidx.activity:activity-compose:${Versions.COMPOSE_ACTIVITY}" }
    val composeLiveData by lazy { "androidx.compose.runtime:runtime-livedata:${Versions.COMPOSE}" }
    val composeViewModel by lazy { "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.COMPOSE_VIEW_MODEL}" }
    val composeMaterial by lazy { "androidx.compose.material:material:${Versions.COMPOSE}" }
    val composeUiPreview by lazy { "androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE}" }
    val composeIconsCore by lazy { "androidx.compose.material:material-icons-core:${Versions.COMPOSE}" }
    val composeIconsExtended by lazy { "androidx.compose.material:material-icons-extended:${Versions.COMPOSE}" }

    // ... Material Design
    val material by lazy { "com.google.android.material:material:${Versions.MATERIAL}" }

    // ... Hilt
    val hilt by lazy { "com.google.dagger:hilt-android:${Versions.HILT}" }
    val hiltCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.HILT}" }

    // ... CameraX
    val camerax by lazy { "androidx.camera:camera-camera2:${Versions.CAMERAX}" }
    val cameraxLifecycle by lazy { "androidx.camera:camera-lifecycle:${Versions.CAMERAX}" }
    val cameraxView by lazy { "androidx.camera:camera-view:${Versions.CAMERAX_VIEW}" }

    // ... Tensorflow Lite
    val tensorflowSupport by lazy { "org.tensorflow:tensorflow-lite-support:${Versions.TENSORFLOW}" }
    val tensorflowMetadata by lazy { "org.tensorflow:tensorflow-lite-metadata:${Versions.TENSORFLOW}" }
    val tensorflowGpu by lazy { "org.tensorflow:tensorflow-lite-gpu:${Versions.TENSORFLOW_GPU}" }

    // ... Glide (https://github.com/skydoves/Landscapist)
    val glideCompose by lazy { "com.github.skydoves:landscapist-glide:${Versions.GLIDE}" }

    // ... Logger (https://github.com/orhanobut/logger)
    val logger by lazy { "com.orhanobut:logger:${Versions.LOGGER}" }

}