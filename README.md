# KMM-Arch-PetSearch


**PetSearch** is an effort to explore and showcase the Kotlin-Multiplatform possibilities.
In this project, I have tried to implement the best practices of mobile application development that I have learned so far.
With that said, just like other humans, I make mistakes (sometimes, really stupid). By no means, this project should be seen as a standard template for KMM development.

### Android Screenshots
<div id="android" align="start">
  <img src="https://user-images.githubusercontent.com/22452092/189471037-f8132942-e39b-416d-9498-536da1764cf4.png" width="640"/>
</div>

### iOS Screenshots
<div id="android" align="start">
  <img src="https://user-images.githubusercontent.com/22452092/189471027-18cc52bf-2256-4f20-b9cb-dc33f64698c6.jpg" width="640"/>
</div>

## Project Setup

### PetFinder Api
- Generate [PetFinder](https://www.petfinder.com) ApiKey and Secret from [here](https://www.petfinder.com/developers/).
- Create `apikey.properties` in `root` dir
- Add the following in properties file
    ```
    API_KEY=<YOUR_API_KEY>
    API_SECRET=<YOUR_API_SECRET>
    ```
- Run `./gradlew generateBuildKonfig`


### Android
- Java 11
- You require [Android Studio Chipmunk](https://developer.android.com/studio/releases)
- Install Kmm Plugin. Checkout [this setup guide](https://kotlinlang.org/docs/kmm-setup.html).
- Create `keystore.properties` in `root` dir and add the following lines in the file. Values can be anything as long as you're not generating a signed APK
    ```
    STORE_FILE=<YOUR_STORE_FILE>
    STORE_PASSWORD=<YOUR_STORE_PASSWORD>
    KEY_ALIAS=<YOUR_KEY_ALIAS>
    KEY_PASSWORD=<YOUR_KEY_PASSWORD>
    ```


### Opening iOS Project
- Navigate to ios directory & open `.xcworkspace` & not `.xcodeproj`




## Libraries Used
### Android
* [Jetpack Compose](https://developer.android.com/jetpack/compose)
    * [Material 3](https://m3.material.io/get-started)
    * [Coil](https://coil-kt.github.io/coil/compose/)
    * [Navigation](https://developer.android.com/jetpack/compose/navigation)
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [Accompanist](https://github.com/google/accompanist)
* [Leakcanary](https://github.com/square/leakcanary)

### Kmm - Shared
* [Ktor](https://ktor.io/)
* [Realm-kotlin](https://github.com/realm/realm-kotlin)
* [Multiplatform Settings](https://github.com/russhwolf/multiplatform-settings)
* [Kotlinx Serialization](https://ktor.io/docs/kotlin-serialization.html)
* [Coroutines](https://github.com/Kotlin/kotlinx.coroutines#multiplatform)
* [Koin](https://insert-koin.io)
* [Kermit](https://github.com/touchlab/Kermit)
* [Moko-Resource](https://github.com/icerockdev/moko-resources)
* [Multiplatform-paging](https://github.com/kuuuurt/multiplatform-paging)
* [Uuid](https://github.com/benasher44/uuid)

### iOS
* [UIPilot](https://github.com/canopas/UIPilot)
* [SDWebImageSwiftUI](https://github.com/SDWebImage/SDWebImageSwiftUI)
* [ToastSwiftUI](https://github.com/huynguyencong/ToastSwiftUI)


## Known issues
* Paging doesn't work on iOS app. 
* Using Moko-resources on iOS throws an exception.


## Inspiration
Following projects have played a huge role in development of KMM-Arch-PetSearch.
I am grateful to authors of projects below for sharing such highly valuable material.
- [KaMPKit](https://github.com/touchlab/KaMPKit)
- [moko-template](https://github.com/icerockdev/moko-template)
- [Food2Fork-KMM](https://github.com/mitchtabian/Food2Fork-KMM)
- [tv-maniac](https://github.com/c0de-wizard/tv-maniac)
- [kmm-demo by fededri](https://github.com/fededri/kmm-demo)
- [ViewModelNavigationCompose](https://github.com/Frank1234/ViewModelNavigationCompose)
- [MortyComposeKMM](https://github.com/joreilly/MortyComposeKMM)
- [Code Snippets](https://github.com/android/compose-samples)
- [kmm-samples](https://kotlinlang.org/docs/multiplatform-mobile-samples.html)




## License

```
MIT License

Copyright (c) 2022 M Sané Akhtar

Permission is hereby granted, free of charge, to any person obtaining 
a copy of this software and associated documentation files (the "Software"),
to deal in the Software without restriction, including without limitation 
the rights to use, copy, modify, merge, publish, distribute, sublicense, 
and/or sell copies of the Software, and to permit persons to whom the 
Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included
in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL 
THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING 
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS 
IN THE SOFTWARE.

```
