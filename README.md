# 🎬 Jetpack Compose Movie App

A high-performance, scalable, and modern Android application built with **Jetpack Compose** and a fully modular **Clean Architecture**.  
This project demonstrates real-world implementation of **MVI (Model-View-Intent)**, dependency injection with **Hilt**, the **repository pattern**, **data mapping**, **Room database caching**, and centralized **error handling** — all integrated to deliver a rich, reactive movie-browsing experience powered by the [TMDB API](https://www.themoviedb.org/).

---

## 🚀 Tech Highlights

| Category               | Technology/Library                           |
|------------------------|----------------------------------------------|
| Language               | [Kotlin](https://kotlinlang.org/)            |
| UI Toolkit             | [Jetpack Compose](https://developer.android.com/jetpack/compose) |
| Architecture           | Clean Architecture (Multi-module) + MVI     |
| Dependency Injection   | [Dagger Hilt](https://dagger.dev/hilt/)      |
| Networking             | [Retrofit](https://square.github.io/retrofit/) |
| Pagination             | [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) |
| Local Caching          | [Room](https://developer.android.com/jetpack/androidx/releases/room) |
| State Management       | Kotlin StateFlow + MVI                       |
| Image Loading          | [Coil](https://coil-kt.github.io/coil/)     |
| API Provider           | [TMDB API](https://developers.themoviedb.org/) |
| Error Handling         | Centralized Exception Layer                  |

---

## 🧱 Architecture Overview

The project follows **Clean Architecture principles**, with a fully decoupled and testable structure across four core layers:

- **Presentation**: Jetpack Compose UI + MVI (Intent → ViewModel → State)
- **Domain**: Business logic + use cases
- **Data**: Repositories + data sources + DTOs
- **Core**: Shared utilities (error handling, mappers, constants)

This structure ensures:
- ✅ Clear separation of concerns  
- 🧪 Easy testability  
- 🔧 Maintainable and scalable codebase  

---

## 🧠 MVI Pattern

This app implements a **unidirectional data flow** using the **MVI (Model-View-Intent)** design pattern:

- **Intent**: User actions or events
- **ViewModel**: Handles logic, triggers use cases, updates state
- **State**: Immutable UI state, exposed as `StateFlow`
- **Effect**: One-time events (e.g., navigation, snackbars)
- **UI**: Declarative Compose UI that observes and reacts to state

---

## 🎯 Features

- 🔥 Browse **Popular** and **Upcoming** movies
- 🔍 View detailed info for each movie
- ⚡ Reactive UI with **Jetpack Compose + StateFlow**
- 🔀 Smooth infinite scroll with **Paging 3**
- 💉 Dependency injection with **Dagger Hilt**
- 💾 Offline caching via **Room Database**
- 🧩 Clean mapping between layers (DTO → Domain → UI)
- 🧱 Multi-module setup following **Clean Architecture**
- ❌ Centralized error handling with fallback UI states
- 🌙 Full support for **Dark Mode**

---

## 📸 Screenshots

| Popular Screen | Upcoming Screen | Movie Details |
|----------------|---------------|------------------|
| ![Popular](https://github.com/JonathnEhab/JatpackComposeMovieApp/blob/master/Popular.png) | ![Upcoming](https://raw.githubusercontent.com/JonathnEhab/JatpackComposeMovieApp/refs/heads/master/Upcoming.png)  | ![Upcoming](https://github.com/JonathnEhab/JatpackComposeMovieApp/blob/master/Details.png) |


> 📁 *Place your screenshots in the `screenshots` folder inside the root project directory.*

---

## 🛠 Getting Started

### 🔐 Prerequisites

- Android Studio **Hedgehog** or newer
- Kotlin 1.9+
- Minimum SDK 24+
- A valid [TMDB API Key](https://www.themoviedb.org/settings/api)

### 🚀 How to Run

1. Clone the repo:
   ```bash
   git clone https://github.com/your-username/jetpack-compose-movie-app.git
