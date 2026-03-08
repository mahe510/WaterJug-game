🧠 Water Jug Puzzle Game (AI Solver)

An Android puzzle game based on the classical Water Jug Problem from Artificial Intelligence.
The goal is to measure a specific quantity of water using jugs with fixed capacities by performing operations such as fill, empty, and pour.
The application also includes an AI solver that finds optimal solutions using Breadth-First Search (BFS).

📱 Features

🎮 Puzzle Gameplay

•	Interactive water jug simulation
•	Fill, empty, and pour operations
•	Real-time water level visualization

🧩 Multiple Levels

The game includes 20 puzzle levels with increasing difficulty:
•	Easy
•	Medium
•	Hard
•	Advanced Hard

🤖 AI Powered Hint System

The hint system analyzes the current puzzle state and suggests the optimal next move.

⚡ Auto Solve
The AI can automatically compute and demonstrate the complete optimal solution.

📊 Score System
•	Scores are calculated based on:
•	Number of moves
•	Time taken
•	Hint usage

💾 Progress Tracking
The app stores:
•	Best score per level
•	Total levels completed
•	User progress
All data is stored locally using SharedPreferences.

🧠 AI Algorithm Used
The puzzle is modeled as a state-space search problem.
Each configuration of water inside the jugs represents a state.

The solver uses Breadth-First Search (BFS) to explore possible states and find the shortest sequence of actions required to reach the target amount.

BFS guarantees:
✔ Optimal solution (minimum moves)
✔ Complete search of valid states
✔ Efficient state exploration using visited state tracking

🛠 Tech Stack

Technology & Purpose

Kotlin -Programming Language
Android Studio -Development Environment
Jetpack Compose -UI Framework
BFS Algorithm -AI Solver
SharedPreferences -Local Storage
Git & GitHub -Version Control

🎮 Game Controls

•	Players can perform the following operations:
•	Fill Jug
•	Empty Jug
•	Pour water between jugs

Goal: Achieve the target water quantity in any jug.

📂 Project Structure:

App/
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

Clone the repository:

•	Git clone https://github.com/mahe510/WaterJug-game.git

•	Open the project in Android Studio
•	Sync Gradle
•	Run on emulator or Android device

📦 APK Download

You can download and install the APK here:
https://drive.google.com/your-apk-link
