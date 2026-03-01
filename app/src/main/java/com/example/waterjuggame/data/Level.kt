package com.example.waterjuggame.data

enum class Difficulty {
    EASY,
    MEDIUM,
    HARD
}

data class Level(
    val id: Int,
    val capacities: List<Int>,
    val goal: Int,
    val difficulty: Difficulty
)
