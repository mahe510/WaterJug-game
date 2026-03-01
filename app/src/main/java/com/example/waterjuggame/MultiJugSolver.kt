package com.example.waterjuggame

import java.util.*

data class MultiState(val amounts: List<Int>)

data class MultiMove(
    val from: MultiState,
    val to: MultiState,
    val action: String
)

object MultiJugSolver {

    fun solve(capacities: List<Int>, goal: Int): List<MultiMove> {

        val start = MultiState(List(capacities.size) { 0 })

        val visited = mutableSetOf<MultiState>()
        val queue: Queue<Pair<MultiState, List<MultiMove>>> = LinkedList()

        queue.add(start to emptyList())

        while (queue.isNotEmpty()) {

            val (current, path) = queue.remove()

            if (current in visited) continue
            visited.add(current)

            if (current.amounts.any { it == goal }) {
                return path
            }

            val nextMoves = generateMoves(current, capacities)

            for ((next, action) in nextMoves) {
                if (next !in visited) {
                    queue.add(next to path + MultiMove(current, next, action))
                }
            }
        }

        return emptyList()
    }

    private fun generateMoves(
        state: MultiState,
        caps: List<Int>
    ): List<Pair<MultiState, String>> {

        val results = mutableListOf<Pair<MultiState, String>>()

        for (i in caps.indices) {

            // Fill jug i
            val fill = state.amounts.toMutableList()
            fill[i] = caps[i]
            results.add(MultiState(fill) to "Fill Jug ${i+1}")

            // Empty jug i
            val empty = state.amounts.toMutableList()
            empty[i] = 0
            results.add(MultiState(empty) to "Empty Jug ${i+1}")

            // Pour i → j
            for (j in caps.indices) {
                if (i == j) continue

                val pour = state.amounts.toMutableList()

                val transfer = minOf(
                    pour[i],
                    caps[j] - pour[j]
                )

                pour[i] -= transfer
                pour[j] += transfer

                results.add(
                    MultiState(pour) to "Pour ${i+1} → ${j+1}"
                )
            }
        }

        return results
    }
}
