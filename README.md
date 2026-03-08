🧠 Water Jug Puzzle Game (AI Solver)

An Android puzzle game based on the classical Water Jug Problem from Artificial Intelligence.

The objective is to measure a specific quantity of water using jugs with fixed capacities by performing operations such as fill, empty, and pour.

The application also includes an AI solver that finds optimal solutions using Breadth-First Search (BFS).

📱 Features
Puzzle Gameplay

Interactive water jug simulation

Fill, empty, and pour operations

Visual water level representation

Multiple Levels

The game contains 20 puzzle levels with increasing difficulty:

Easy

Medium

Hard

Advanced Hard

AI Hint System

The hint system analyzes the current state and suggests the optimal next move.

Auto Solve

The AI solver demonstrates the complete optimal solution path.

Score System

Scores depend on:

Number of moves

Time taken

Hint usage

Progress Tracking

The application stores:

Best score for each level

Levels completed

Player progress

All progress is stored locally using SharedPreferences.

🧠 AI Algorithm Used

The puzzle is modeled as a state-space search problem.

Each configuration of water inside the jugs represents a state.

The solver uses Breadth-First Search (BFS) to explore possible states and find the shortest sequence of moves required to reach the target amount.

BFS guarantees:

Optimal solution (minimum number of moves)

Complete search of valid states

Efficient exploration using visited state tracking

🛠 Tech Stack
Technology	Purpose
Kotlin	Programming Language
Android Studio	Development Environment
Jetpack Compose	UI Framework
BFS Algorithm	AI Solver
SharedPreferences	Local Storage
Git & GitHub	Version Control
🎮 Game Controls

Players can perform the following actions:

Fill a jug

Empty a jug

Pour water between jugs

The goal is to achieve the target water quantity in any jug.

📂 Project Structure
app/
 ├── data/
 │    ├── LevelRepository.kt
 │    ├── UserManager.kt
 │
 ├── ui/
 │    ├── GameScreen.kt
 │    ├── LevelSelectScreen.kt
 │
 ├── solver/
 │    ├── WaterJugSolver.kt
 │    ├── MultiJugSolver.kt
 │    ├── MultiJugHintSolver.kt
 │
 ├── components/
 │    ├── JugView.kt
🚀 How to Run

Clone the repository

git clone https://github.com/mahe510/WaterJug-game.git

Open the project in Android Studio

Sync Gradle

Run the application on an emulator or Android device

📦 APK Download

Download the APK here:

https://drive.google.com/file/d/1aR54Rdr5KRBEvPLcwAvPHRse7k-OxxfR/view
👨‍💻 Author

Mahe Barnwal
B.Tech Computer Science & Engineering

GitHub:
https://github.com/mahe510
