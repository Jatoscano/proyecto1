plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    //Plugin de Aplicacion de Room
    id("com.google.devtools.ksp")
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
    //Refresh-Layout
    implementation (libs.androidx.swiperefreshlayout)
    //Coordinator Layout
    implementation(libs.androidx.coordinatorlayout)
     */

    //Room - Database
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:2.6.1")

    //Bundles
    //Retrofit - Gson
    implementation(libs.bundles.retrofit)
    //Corrutinas
    implementation (libs.kotlinx.coroutines.android)
    //Coil
    implementation(libs.coil)
    //Glide
    implementation (libs.glide)
    //Layouts -> Refresh - Coordinator
    implementation(libs.bundles.layouts)



}