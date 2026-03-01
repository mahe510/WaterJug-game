package com.example.waterjuggame.data

import java.util.*

data class MultiJugMove(val description: String)

object MultiJugHintSolver {

    // Limit search so phone never freezes
    private const val MAX_STATES = 4000

    fun getNextMove(
        capacities: List<Int>,
        start: List<Int>,
        goal: Int
    ): MultiJugMove? {

        if (start.any { it == goal }) return MultiJugMove("Already solved!")

        val visited = mutableSetOf<List<Int>>()
        val queue: Queue<Pair<List<Int>, List<String>>> = LinkedList()

        queue.add(start to emptyList())
        visited.add(start)

        var explored = 0

        while (queue.isNotEmpty() && explored < MAX_STATES) {
            val currentNode = queue.poll() ?: break
            val state = currentNode.first
            val path = currentNode.second

            explored++

            // Goal reached
            if (state.any { it == goal }) {
                return if (path.isNotEmpty())
                    MultiJugMove(path.first())
                else null
            }

            // Generate next states
            for (next in generateMoves(state, capacities)) {

                if (next.state !in visited) {
                    visited.add(next.state)
                    queue.add(next.state to (path + next.action))
                }
            }
        }

        return MultiJugMove("Try redistributing water")
    }

    // All legal moves from a state
    private fun generateMoves(
        state: List<Int>,
        capacities: List<Int>
    ): List<MoveResult> {

        val results = mutableListOf<MoveResult>()

        for (i in state.indices) {

            // Fill
            if (state[i] < capacities[i]) {
                val newState = state.toMutableList()
                newState[i] = capacities[i]
                results.add(MoveResult(newState, "Fill Jug ${i + 1}"))
            }

            // Empty
            if (state[i] > 0) {
                val newState = state.toMutableList()
                newState[i] = 0
                results.add(MoveResult(newState, "Empty Jug ${i + 1}"))
            }

            // Pour i → j
            for (j in state.indices) {
                if (i == j) continue

                val amount = minOf(
                    state[i],
                    capacities[j] - state[j]
                )

                if (amount > 0) {
                    val newState = state.toMutableList()
                    newState[i] -= amount
                    newState[j] += amount

                    results.add(
                        MoveResult(newState, "Pour Jug ${i + 1} → Jug ${j + 1}")
                    )
                }
            }
        }

        return results
    }

    private data class MoveResult(
        val state: List<Int>,
        val action: String
    )
}
