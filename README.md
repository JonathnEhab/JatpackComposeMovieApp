# 🎬 Jetpack Compose Movie App

A high-performance, scalable, and modern Android application built with **Jetpack Compose** and a fully modular **Clean Architecture**.  
This project demonstrates real-world implementation of **MVI (Model-View-Intent)**, dependency injection with **Hilt**, the **repository pattern**, **data mapping**, **Room database caching**, **Paging 3**, and centralized **error handling** — all integrated to deliver a rich, reactive movie-browsing experience powered by the [TMDB API](https://www.themoviedb.org/).

---

## 🚀 Tech Highlights

| Category               | Technology/Library                           |
|------------------------|----------------------------------------------|
| Language               | [Kotlin](https://kotlinlang.org/)            |
| UI Toolkit             | [Jetpack Compose](https://developer.android.com/jetpack/compose) |
| Architecture           | Clean Architecture (Multi-module) + MVI     |
| Dependency Injection   | [Dagger Hilt](https://dagger.dev/hilt/)      |
| Networking             | [Retrofit](https://square.github.io/retrofit/) |
| Local Caching          | [Room](https://developer.android.com/jetpack/androidx/releases/room) |
| Pagination             | [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) |
| State Management       | Kotlin StateFlow + MVI                       |
| Image Loading          | [Coil](https://coil-kt.github.io/coil/)     |
| API Provider           | [TMDB API](https://developers.themoviedb.org/) |
| Error Handling         | Centralized Exception Layer                  |

---

## 🧱 Architecture Overview

The project follows **Clean Architecture principles**, with a fully decoupled and testable structure across four core layers:


This structure ensures:

- Separation of concerns  
- Easy unit and UI testing  
- Maintainable and scalable codebase  

---

## 🧠 MVI Pattern

This app follows a **unidirectional data flow** using the MVI design pattern:

- **Intent**: Represents user actions or system events.  
- **ViewModel**: Handles logic and transforms intents into states.  
- **State**: Immutable and observable via StateFlow.  
- **Effect**: One-time events (e.g., toast, navigation).  
- **UI**: Declarative Compose UI that reacts to state updates.

---

## 🎯 Features

- 🔥 Browse **Popular** and **Upcoming** movies
- 🧾 View full **Movie Details**
- 🔄 **Endless scrolling** using **Paging 3**
- 🧠 Reactive UI powered by **Jetpack Compose + StateFlow**
- 💉 Modular and testable architecture with **Dagger Hilt**
- 💾 Offline support with **Room Database**
- 🧩 Custom data mapping layer for clean DTO → Domain → UI conversion
- ❌ Robust error handling with fallback UI states
- 🌙 Full **Dark Mode** support
- 📦 Multi-module project setup

---

## 📸 Screenshots

> *(Upload screenshots to `/screenshots` folder and reference them below)*

| Home Screen | Movie Details | Error State |
|-------------|----------------|-------------|
| ![Popular]("C:\Users\Jonathan\Downloads\Popular.jpg") | ![Upcoming]("C:\Users\Jonathan\Downloads\Upcoming.jpg") | ![Details]("C:\Users\Jonathan\Downloads\Details.jpg") |

---


