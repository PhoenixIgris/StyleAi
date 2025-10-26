
---

## 🧠 Modular Architecture

StyleAi follows a **multi-module architecture** for scalability, testability, and separation of concerns.

| Layer | Description |
|-------|--------------|
| **build-logic** | Contains reusable Gradle convention plugins for consistent module configuration. |
| **core** | Core logic: data, network, analytics, and model definitions. |
| **features** | Each feature is developed independently and can be scaled easily. |
| **designSystem** | Centralized UI toolkit — components, color schemes, typography. |
| **database & datastore** | Local storage and persistent settings. |
| **network** | API layer with Ktor for shared networking. |

---

## ⚙️ Build Configuration

### 🧱 Android SDK
| Property | Value |
|-----------|--------|
| `compileSdk` | 35 |
| `minSdk` | 29 |
| `targetSdk` | 35 |

### 🔧 Core Plugins
| Plugin | Version |
|---------|----------|
| Android Gradle Plugin | 8.9.0 |
| Kotlin | 2.1.0 |
| Compose | 1.7.1 |
| KSP | 2.1.0-1.0.29 |
| BuildKonfig | 0.15.1 |
| Detekt | 1.23.6 |
| KtLint | 12.1.1 |

---

## 📦 Key Libraries

| Category | Libraries |
|-----------|------------|
| **Core** | Kotlin Coroutines, AndroidX Core, AppCompat |
| **Lifecycle & Navigation** | JetBrains Lifecycle, JetBrains Navigation Compose |
| **Dependency Injection** | Koin 4.0.0-RC1 |
| **Network** | Ktor 3.0.0, Chucker, Napier, Result, UUID |
| **Database** | Room 2.7.0-alpha12, SQLite 2.5.0 |
| **DataStore** | Multiplatform Settings, Context Provider |
| **Compose UI** | Lottie, Accompanist Permissions |
| **Serialization & DateTime** | kotlinx-serialization-json, kotlinx-datetime |
| **Testing** | JUnit 4.13.2, Kotlin Test |

---

## 🏗️ Build Logic (Convention Plugins)

StyleAi uses a **custom Gradle build logic system** to maintain consistent configurations across all modules.  
Plugins are defined inside the `build-logic` module and applied project-wide.

### Custom Plugins
- `styleai.android.application.convention.plugin`
- `styleai.config.convention.plugin`
- `styleai.module.convention.plugin`
- `styleai.multiplatform.library.convention.plugin`
- `styleai.multiplatform.compose.library.convention.plugin`
- `styleai.android.room.convention.plugin`
- `styleai.feature.convention.plugin`

These ensure:
✅ Unified build configuration  
✅ Clean Gradle files  
✅ Simplified dependency management  

---

## 🧠 Features

### ✅ Current
- 🖼️ **Image selection & handling**
- 🧭 **Panorama view integration**
- 🧩 **Interactive SVG support**
- 📊 **Analytics tracking**

### 🔮 Planned
- 🤖 **AI-based outfit and makeup recommendation**
- 🧍 **Body and face structure detection**
- 🪞 **Color-based styling suggestions**
- ☁️ **Cloud sync and personalization**
- 🧠 **Smart recommendation engine**

---

## 🧰 Setup & Run

### Prerequisites
- **Android Studio Ladybug | IntelliJ IDEA 2024.1+**
- **JDK 17+**
- **Gradle 8.9+**
- **Kotlin 2.1.0+**

### Clone & Run
```bash
# Clone the repository
git clone https://github.com/PhoenixIgris/StyleAi.git
cd StyleAi

# Open in Android Studio or IntelliJ IDEA
# Sync Gradle and run
