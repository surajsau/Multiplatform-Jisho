# Multiplatform-Jisho
Kotlin Multiplatform Japanese Dictionary App.

🚧 This is still a Work in Progress. Wishlist of features are listed up in the [Projects](https://github.com/users/surajsau/projects/1).

## Mutliplatform
This project has the [Kotlin Native memory model](https://github.com/JetBrains/kotlin/blob/0b871d7534a9c8e90fb9ad61cd5345716448d08c/kotlin-native/NEW_MM.md) enabled (check `gradle.properties`)

```.properties

kotlin.native.binary.memoryModel=experimental
```

### Architecture
Everything except from the UI layer (i.e., the domain and data layers) have been implemented in the `:shared` module.

## Libraries used
- [Kotlin](https://kotlinlang.org/) `1.6.10`
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
- [Koin](https://github.com/InsertKoinIO/koin)
- [SQLDelight](https://github.com/cashapp/sqldelight)
- [KMP-NativeCoroutines](https://github.com/rickclephas/KMP-NativeCoroutines) `0.11.4-new-mm`
- [Firebase Cloud Storage](https://firebase.google.com/docs/storage) (for storing the preloaded database file)
- [neumorphic](https://github.com/costachung/neumorphic) (Neumorphism in SwiftUI)

## References
- [compose-neumorphism](https://github.com/sridhar-sp/compose-neumorphism) Used as a reference for custom implementation of Neumorphism in Jetpack Compose
- [hackingswift](https://www.hackingwithswift.com) for anything SwiftUI
- [droidkaigi 2021 App](https://github.com/DroidKaigi/conference-app-2021) for the Unidirectional ViewModel implementation

## Branches
- `shared` : Any changes done outside the `iosApp` and `androidApp` modules
- `android` : Changes made to the `androidApp` module
- `ios` : Changes made to the `iosApp` module

## Data Source
Preloaded `.db` file has been generated using the [`create_database.py`](script/create_database.py) script. The following data sources have been parsed

#### [JMDict](http://www.edrdg.org/wiki/index.php/JMdict-EDICT_Dictionary_Project)
The main data source for the dictionary and contains roughly over 170,000 entries with informations like meaning, kana-reading, sentence samples, particles of speeches etc.

#### [Kanjidic2](http://www.edrdg.org/wiki/index.php/KANJIDIC_Project)
This includes Kanji related informations like reading, stroke count etc.

#### [Tanaka Corpus](https://tatoeba.org/en/downloads)
This is a database of sentences mapped in various languages (primarily JP-EN). Multiple lanugages could have been chosen but instead went with the English Japanese Sentence pairs dataset.

#### [JLPT Resources by Jonathan Walters](http://www.tanos.co.uk/jlpt/)
This contains a roughly comprehensive list of vocabulary divided on the basis of JLPT level.

## License

```
CCopyright 2022 Suraj Sau

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
