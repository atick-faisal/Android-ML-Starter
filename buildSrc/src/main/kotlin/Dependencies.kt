// ... Project dependencies

object Dependencies {

    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.CORE_KTX}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.APP_COMPAT}" }
    val composeUi by lazy { "androidx.compose.ui:ui:${Versions.COMPOSE}" }
    val composeMaterial by lazy { "androidx.compose.material:material:${Versions.COMPOSE}" }
    val composeUiPreview by lazy { "androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE}" }
    val lifecycleKtx by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE_KTX}" }
    val activityCompose by lazy { "androidx.activity:activity-compose:${Versions.ACTIVITY_COMPOSE}" }
    val material by lazy { "com.google.android.material:material:${Versions.MATERIAL}" }

}