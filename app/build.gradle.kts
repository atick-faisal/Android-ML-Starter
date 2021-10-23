plugins {
    id("com.android.application")
    id("kotlin-android")
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
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    implementation(Dependencies.activityCompose)

    // ... Jetpack Compose
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeUiPreview)

    // ... Material Design
    implementation(Dependencies.material)
}