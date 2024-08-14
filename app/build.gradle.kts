plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.krinpatelproject2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.krinpatelproject2"
        minSdk = 27
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
}

dependencies {
    testImplementation("org.testng:testng:6.9.6")
    val room_version = "2.6.1"


    implementation ("androidx.viewpager2:viewpager2:1.0.0")


    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)


}