plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.toscano.proyecto1"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.toscano.proyecto1"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    //Caracteristicas del ViewBinding
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    /*
    //Manera antigua
    //Retrofit
    implementation (libs.retrofit)
    //Gson
    implementation(libs.converter.gson)
     */

    //Bundles
    //Retrofit
    implementation(libs.bundles.retrofit)
    //Corrutinas
    implementation (libs.kotlinx.coroutines.android)
    //Coil
    implementation(libs.coil)
    //Glide
    implementation (libs.glide)
    //Refresh-Layout
    implementation (libs.androidx.swiperefreshlayout)
}