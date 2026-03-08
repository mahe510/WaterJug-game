Water Jug Puzzle Game with AI Solver

Android Application Project Documentation

Developer: Mahe Barnwal
Program: B.Tech Computer Science & Engineering
Platform: Android
Language: Kotlin
IDE: Android Studio

1. Project Overview

The Water Jug Puzzle Game is an Android mobile application based on the classical Water Jug Problem, a well-known problem in Artificial Intelligence and algorithm design. The objective of the puzzle is to measure a specific quantity of water using jugs with fixed capacities.

Players interact with the puzzle by performing operations such as filling, emptying, and pouring water between jugs to achieve the target quantity. The application also incorporates an AI-based solver that uses Breadth-First Search (BFS) to compute optimal solutions.

The project demonstrates how state-space search algorithms from Artificial Intelligence can be applied to solve real-world logical puzzles in an interactive mobile environment.

2. Objectives of the Project

The main objectives of this project are:

To implement the classical Water Jug Problem as an interactive mobile game.

To demonstrate the application of Artificial Intelligence search algorithms.

To provide users with hints and automatic solutions generated using AI.

To create an engaging puzzle-based learning experience for users.

To design a clean and modern Android user interface using Jetpack Compose.

3. Key Features

The application provides the following features:

Puzzle Gameplay

Interactive water jug puzzle mechanics

Fill, empty, and pour operations

Real-time visual representation of jug water levels

Multiple Difficulty Levels

The game contains 20 puzzle levels divided into difficulty categories:

Easy

Medium

Hard

Advanced

Levels gradually increase in complexity by introducing larger capacities and multiple jugs.

AI Hint System

The hint system analyzes the current game state and suggests the optimal next move using a search algorithm.

Auto-Solve Feature

The application can automatically demonstrate the optimal sequence of moves required to solve the puzzle.

Score System

Players receive scores based on:

Number of moves

Time taken

Hint usage

Progress Tracking

The application stores:

Best score for each level

Total levels completed

Player progress

This data is stored locally using SharedPreferences.

4. Technology Stack

The following technologies were used to develop the application.

Programming Language

Kotlin

Development Environment

Android Studio

UI Framework

Jetpack Compose

Data Storage

SharedPreferences

Version Control

Git & GitHub

Algorithm

Breadth-First Search (BFS)

5. Artificial Intelligence Implementation

The Water Jug problem is modeled as a state-space search problem.

Each configuration of water levels inside the jugs represents a state. The AI algorithm explores all possible states by applying valid operations such as:

Filling a jug

Emptying a jug

Pouring water from one jug to another

The Breadth-First Search (BFS) algorithm is used to explore the state space.

BFS systematically examines all possible states level-by-level and guarantees that the first solution found is the optimal solution with the minimum number of moves.

A visited state set is maintained to avoid revisiting previously explored states, ensuring efficient exploration of the state space.

This approach allows the application to provide both:

Optimal hints

Automatic puzzle solving

6. Application Workflow

The application follows the workflow below:

User launches the application.

User selects a puzzle level.

The game screen displays jug capacities and the target goal.

The player performs actions such as filling, emptying, or pouring water.

The system tracks the number of moves and time taken.

The AI hint system suggests the next optimal move when requested.

The auto-solve feature demonstrates the optimal solution.

When the goal is achieved, the level is marked as completed.

The player's score and progress are saved.

7. User Interface Design

The application interface is developed using Jetpack Compose, which provides a modern declarative UI approach for Android applications.

The interface includes:

A level selection screen

Animated water jug visualization

Interactive control buttons

Real-time move and timer display

Completion and scoring screen

The design focuses on simplicity, usability, and clear visual feedback for puzzle interactions.

8. Challenges Faced During Development

Several challenges were encountered during the development process:

Designing solvable puzzle levels with appropriate difficulty

Preventing infinite loops in the solver algorithm

Managing state transitions efficiently

Ensuring the AI solver does not freeze the application

Designing a responsive and interactive UI

These challenges were addressed through careful algorithm design and testing.

9. Future Improvements

Possible improvements for the application include:

Online leaderboard system

Multiplayer puzzle challenges

Additional puzzle variations

Advanced AI algorithms such as A* search

Cloud-based progress synchronization

Animated water physics

10. Conclusion

The Water Jug Puzzle Game successfully demonstrates the practical application of Artificial Intelligence search algorithms in mobile application development.

By combining algorithmic problem solving with interactive Android UI design, the project provides both an educational and entertaining puzzle experience. The integration of AI-powered hints and automatic solving makes the application a useful tool for understanding state-space search techniques in Artificial Intelligence.
