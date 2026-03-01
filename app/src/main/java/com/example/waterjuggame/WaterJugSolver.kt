package com.example.waterjuggame

import java.util.LinkedList
import java.util.Queue

// A state of the puzzle
data class JugState(val a: Int, val b: Int)

// A transition between states
data class JugMove(
    val from: JugState,
    val to: JugState,
    val action: String
)

object WaterJugSolver {

    // BFS AI Solver
    fun solve(
        capA: Int,
        capB: Int,
        goal: Int,
        startA: Int = 0,
        startB: Int = 0
    ): List<JugMove>
    {

        val visited = mutableSetOf<JugState>()
        val queue: Queue<Pair<JugState, List<JugMove>>> = LinkedList()

        val start = JugState(startA, startB)

        queue.add(start to emptyList())

        while (queue.isNotEmpty()) {

            val (current, path) = queue.remove()

            if (current in visited) continue
            visited.add(current)

            if (current.a == goal || current.b == goal) {
                return path
            }

            val nextStates = generateMoves(current, capA, capB)

            for ((next, action) in nextStates) {
                if (next !in visited) {
                    val move = JugMove(current, next, action)
                    queue.add(next to path + move)
                }
            }
        }

        return emptyList()
    }

    private fun generateMoves(state: JugState, capA: Int, capB: Int): List<Pair<JugState, String>> {

        val (a, b) = state
        val moves = mutableListOf<Pair<JugState, String>>()

        moves.add(JugState(capA, b) to "Fill A")
        moves.add(JugState(a, capB) to "Fill B")

        moves.add(JugState(0, b) to "Empty A")
        moves.add(JugState(a, 0) to "Empty B")

        val pourAB = minOf(a, capB - b)
        moves.add(JugState(a - pourAB, b + pourAB) to "Pour A → B")

        val pourBA = minOf(b, capA - a)
        moves.add(JugState(a + pourBA, b - pourBA) to "Pour B → A")

        return moves
    }
}
