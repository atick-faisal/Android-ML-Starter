plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = BuildConfigData.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = "ai.andromeda.mlstarter"
        minSdk = BuildConfigData.MIN_SDK_VERSION
        targetSdk = BuildConfigData.TARGET_SDK_VERSION
        versionCode = BuildConfigData.VERSION_CODE
        versionName = BuildConfigData.VERSION_NAME

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("debug") {
            buildConfigField(
                type = "String",
                name = "BASE_URL",
                value = "\"http:://raspberrypi.local/\""
            )
            versionNameSuffix = "-debug"
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            getByName("debug") {
                buildConfigField(
                    type = "String",
                    name = "BASE_URL",
                    value = "\"http:://raspberrypi.local/\""
                )
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        mlModelBinding = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.lifecycleKtx)
    implementation(Dependencies.activityKtx)

    // ... Navigation
    implementation(Dependencies.navFragmentKtx)
    implementation(Dependencies.navUiKtx)

    // ... Jetpack Compose
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeFoundation)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeUiPreview)
    implementation(Dependencies.composeActivity)
    implementation(Dependencies.composeLiveData)
    implementation(Dependencies.composeViewModel)
    implementation(Dependencies.composeIconsCore)
    implementation(Dependencies.composeIconsExtended)

    // ... Material Design
    implementation(Dependencies.material)

    // ... Hilt
    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltCompiler)

    // ... CameraX
    implementation(Dependencies.camerax)
    implementation(Dependencies.cameraxLifecycle)
    implementation(Dependencies.cameraxView)

    // ... Tensorflow Lite
    implementation(Dependencies.tensorflowSupport)
    implementation(Dependencies.tensorflowMetadata)
    implementation(Dependencies.tensorflowGpu)

    // ... Retrofit & OkHttp
    implementation(Dependencies.retrofit)
    implementation(Dependencies.gsonConverter)
    implementation(Dependencies.scalarConverter)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.loggingInterceptor)

    // ... Glide Compose
    implementation(Dependencies.glideCompose)

    // ... Logger
    implementation(Dependencies.logger)
}

// ... Required for Hilt
// ... Ref: (https://dagger.dev/hilt/gradle-setup.html)
kapt {
    correctErrorTypes = true
}