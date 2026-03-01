package com.example.waterjuggame

import java.util.PriorityQueue
import kotlin.math.abs

data class AStarNode(
    val a: Int,
    val b: Int,
    val cost: Int,
    val path: List<String>
)

object AStarWaterJugSolver {

    fun solve(capA: Int, capB: Int, goal: Int): List<String> {

        val visited = mutableSetOf<Pair<Int, Int>>()

        val pq = PriorityQueue<AStarNode>(
            compareBy { it.cost + heuristic(it.a, it.b, goal) }
        )

        pq.add(AStarNode(0, 0, 0, emptyList()))

        while (pq.isNotEmpty()) {

            val current = pq.poll()

            // Goal reached
            if (current.a == goal || current.b == goal)
                return current.path

            if (!visited.add(current.a to current.b)) continue

            fun push(a: Int, b: Int, action: String) {
                pq.add(
                    AStarNode(
                        a,
                        b,
                        current.cost + 1,
                        current.path + action
                    )
                )
            }

            // Fill operations
            push(capA, current.b, "Fill A")
            push(current.a, capB, "Fill B")

            // Empty operations
            push(0, current.b, "Empty A")
            push(current.a, 0, "Empty B")

            // Pour A -> B  (IMPORTANT: use -> not →)
            val pourAB = minOf(current.a, capB - current.b)
            push(current.a - pourAB, current.b + pourAB, "Pour A -> B")

            // Pour B -> A  (IMPORTANT: use -> not →)
            val pourBA = minOf(current.b, capA - current.a)
            push(current.a + pourBA, current.b - pourBA, "Pour B -> A")
        }

        return emptyList()
    }

    // Heuristic = distance from goal
    private fun heuristic(a: Int, b: Int, goal: Int): Int {
        return minOf(abs(goal - a), abs(goal - b))
    }
}
