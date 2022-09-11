# KMM-Arch-PetSearch


**PetSearch** is an effort to explore and showcase the Kotlin-Multiplatform possibilities.<br>
100% Jetpack Compose, 100% SwiftUI. This project is all about learning.<br>
With that said, author has realised that,
<br>
**"The more you know, the more you realize you don't know."** - *Aristotle*

<br>

### Android Screenshots
<div id="android" align="start">
  <img src="https://user-images.githubusercontent.com/22452092/189471037-f8132942-e39b-416d-9498-536da1764cf4.png" width="640"/>
</div>

### iOS Screenshots
<div id="android" align="start">
  <img src="https://user-images.githubusercontent.com/22452092/189471027-18cc52bf-2256-4f20-b9cb-dc33f64698c6.jpg" width="640"/>
</div>



## Project Architecture
PetSearch has blended the following coding practices and techniques to create a flavor of its own.
- Clean Architecture
- Redux MVI
- Feature-wise and Layer-wise Modularized code (Android and Shared)
- Shared ViewModel
- ViewModel Navigation with args (Android and iOS)

<br>

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

<br>

## Known issues
* Paging doesn't work on iOS app. An implementation similar to collectAsLazyPagingItems() in [Compose Paging](https://developer.android.com/reference/kotlin/androidx/paging/compose/package-summary) is required.
* Accessing Moko-resources on iOS throws an exception. Most likely, it is due to improper configuration/set-up either in podSpec or gradle.

<br>

## Code Quality
| Tools | Check command | Fix command |
|-------|---------------|-------------|
| [detekt](https://github.com/arturbosch/detekt) | `./gradlew detekt` | - |
| [ktlint](https://github.com/pinterest/ktlint) | `./gradlew ktlint` | `./gradlew ktlintFormat` |
| [spotless](https://github.com/diffplug/spotless) | `./gradlew spotlessCheck` | `./gradlew spotlessApply`

<br>

## Libraries Used
### Android
* [Jetpack Compose](https://developer.android.com/jetpack/compose)
    * [Material 3](https://m3.material.io/get-started)
    * [Navigation](https://developer.android.com/jetpack/compose/navigation)
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [Accompanist](https://github.com/google/accompanist)
* [Coil](https://coil-kt.github.io/coil/compose/)
* [Leakcanary](https://github.com/square/leakcanary)

### Kmm - Shared
* [Ktor](https://ktor.io/)
* [Realm-kotlin](https://github.com/realm/realm-kotlin)
* [Multiplatform Settings](https://github.com/russhwolf/multiplatform-settings)
* [Kotlinx Serialization](https://ktor.io/docs/kotlin-serialization.html)
* [Coroutines](https://github.com/Kotlin/kotlinx.coroutines#multiplatform)
* [Koin](https://insert-koin.io)
* [Kermit](https://github.com/touchlab/Kermit)
* [Moko-Resources](https://github.com/icerockdev/moko-resources)
* [Multiplatform-paging](https://github.com/kuuuurt/multiplatform-paging)
* [Uuid](https://github.com/benasher44/uuid)

### iOS
* [UIPilot](https://github.com/canopas/UIPilot)
* [SDWebImageSwiftUI](https://github.com/SDWebImage/SDWebImageSwiftUI)
* [ToastSwiftUI](https://github.com/huynguyencong/ToastSwiftUI)


<br>

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


<br>

## License

MIT license. See the [LICENSE file](https://github.com/msa1422/KMM-Arch-PetSearch/blob/master/LICENSE.md) for more information.
