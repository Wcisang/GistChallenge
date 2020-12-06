object Version {

    const val kotlin = "1.4.0"
    const val gradle = "4.1.1"
    const val appCompat = "1.2.0"
    const val coreKtx = "1.3.2"
    const val constraintLayout = "2.0.4"
    const val retrofit = "2.9.0"
    const val okHttp = "4.9.0"
    const val coroutinesAdapter = "0.9.2"
    const val coroutines = "1.4.2"
    const val koin = "2.2.1"
    const val lifecycle = "2.2.0"
    const val paging = "3.0.0-alpha09"
    const val material = "1.2.1"
    const val jackson = "2.11.3"
    const val coil = "1.1.0"
    const val room = "2.2.5"

    //test
    const val jUnit = "4.13.1"
    const val robolectric = "4.4"
    const val espresso = "3.3.0"
    const val mockk = "1.10.3-jdk8"
    const val testCore = "1.3.0"
    const val archTest = "2.1.0"
    const val jUnitExt = "1.1.1"
    const val truth = "1.1"

}

object Libs {

    const val kotlinStd = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}"
    const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Version.lifecycle}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
    const val coreKtx =  "androidx.core:core-ktx:${Version.coreKtx}"
    const val material = "com.google.android.material:material:${Version.material}"
    const val jackson = "com.fasterxml.jackson.module:jackson-module-kotlin:${Version.jackson}"
    const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"

    //Layout
    const val constraintLayout =  "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
    const val paging = "androidx.paging:paging-runtime:${Version.paging}"
    const val coil = "io.coil-kt:coil:${Version.coil}"

    //Networking
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Version.okHttp}"
    const val jacksonConverter = "com.squareup.retrofit2:converter-jackson:${Version.retrofit}"

    //Coroutines
    const val coroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Version.coroutinesAdapter}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"

    //Koin
    const val koinViewModel = "org.koin:koin-android-viewmodel:${Version.koin}"
    const val koin  = "org.koin:koin-android:${Version.koin}"

    //Room
    const val roomCompiler = "androidx.room:room-compiler:${Version.room}"
    const val roomKtx = "androidx.room:room-ktx:${Version.room}"
}

object LibsTest{
    const val jUnit = "junit:junit:${Version.jUnit}"
    const val robolectric = "org.robolectric:robolectric:${Version.robolectric}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Version.espresso}"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Version.espresso}"
    const val mockk = "io.mockk:mockk:${Version.mockk}"
    const val testCoreKtx = "androidx.test:core-ktx:${Version.testCore}"
    const val koinTest = "org.koin:koin-test:${Version.koin}"
    const val archTest = "androidx.arch.core:core-testing:${Version.archTest}"
    const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutines}"
    const val jUnitExt = "androidx.test.ext:junit:${Version.jUnitExt}"
    const val truth = "com.google.truth:truth:${Version.truth}"
}