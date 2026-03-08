# 🧠 Water Jug Puzzle Game (AI Solver)

An Android puzzle game based on the classic **Water Jug Problem**, a well-known Artificial Intelligence search problem.

The objective is to measure a specific quantity of water using a limited number of jugs with fixed capacities.

This project demonstrates **State Space Search using Breadth-First Search (BFS)** to compute optimal solutions automatically.

---

# 📱 Features

• 20 Puzzle Levels with increasing difficulty
• Difficulty progression: Easy → Medium → Hard → Advanced
• Interactive water jug visualization
• Move counter and timer system
• AI Hint system for solving puzzles step-by-step
• Auto-Solve using BFS (Artificial Intelligence search algorithm)
• Best score tracking for each level
• Local user profile with persistent progress
• Clean Material Design UI using Jetpack Compose

---

# 🧩 Gameplay

Players must manipulate the jugs using the following operations:

• Fill a jug completely
• Empty a jug
• Pour water from one jug into another

The goal is to reach an **exact target amount of water** in any jug.

Example puzzle:

```
Jug Capacities: 8L, 5L, 3L
Goal: Measure exactly 4L
```

---

# 🤖 Artificial Intelligence Implementation

The game uses **State Space Search** to compute solutions.

Each configuration of water amounts is treated as a **state**.

The solver explores possible states using:

### Breadth-First Search (BFS)

BFS guarantees:

✔ Optimal solution (minimum number of moves)
✔ No repeated states using visited state tracking
✔ Complete search of the state space

---

# ⚙️ Technologies Used

• **Kotlin**
• **Jetpack Compose**
• **Android Studio**
• **Material Design 3**
• **Breadth First Search Algorithm**
• **SharedPreferences** for local storage

---

# 📂 Project Structure

```
WaterJug-game
│
├── app
│   ├── data
│   │   ├── LevelRepository.kt
│   │   ├── UserManager.kt
│   │   └── MultiJugHintSolver.kt
│   │
│   ├── ui
│   │   ├── GameScreen.kt
│   │   ├── LevelSelectScreen.kt
│   │   └── components
│   │
│   ├── WaterJugSolver.kt
│   └── MultiJugSolver.kt
│
├── gradle
├── build.gradle.kts
└── settings.gradle.kts
```

---

# 🚀 Running the Project

1. Clone the repository

```
git clone https://github.com/mahe510/WaterJug-game.git
```

2. Open the project in **Android Studio**

3. Let Gradle sync complete

4. Run the project on an emulator or Android device.

---


# 🎯 Educational Purpose

This project demonstrates how classic **AI search algorithms** can be applied to real-world puzzles.

It is useful for learning:

• State Space Search
• Breadth-First Search (BFS)
• Problem Modelling in AI
• Android UI development using Jetpack Compose

---

# 👨‍💻 Author

**Mahe Barnwal**

B.Tech Computer Science & Engineering

GitHub:
https://github.com/mahe510

