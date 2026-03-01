package com.example.waterjuggame.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.platform.LocalContext

import com.example.waterjuggame.WaterJugSolver
import com.example.waterjuggame.data.Level
import com.example.waterjuggame.data.MultiJugHintSolver
import com.example.waterjuggame.data.UserManager
import com.example.waterjuggame.ui.components.JugView
import com.example.waterjuggame.ui.theme.BackgroundSoft

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun GameScreen(
    levelNumber: Int,
    level: Level,
    onBack: () -> Unit,
    onLevelCompleted: () -> Unit
) {

    val context = LocalContext.current

    val capacities = level.capacities
    val goal = level.goal
    // Precompute pour combinations (needed for 3+ jug levels)
    val pourPairs = capacities.indices.flatMap { a ->
        capacities.indices.mapNotNull { b ->
            if (a != b) a to b else null
        }
    }.chunked(3)


    var jugs by remember(level) { mutableStateOf(List(capacities.size) { 0 }) }
    var moves by remember(level) { mutableStateOf(0) }
    var lastAction by remember(level) { mutableStateOf("Start solving!") }

    var usedHint by remember(level) { mutableStateOf(false) }
    var usedAutoSolve by remember(level) { mutableStateOf(false) }

    var timeElapsed by remember(level) { mutableStateOf(0) } // in seconds
    var timerStarted by remember(level) { mutableStateOf(false) }

    LaunchedEffect(timerStarted) {
        if (timerStarted) {
            while (true) {
                delay(1000)

                // stop counting immediately when level completes
                if (jugs.any { it == goal }) break

                timeElapsed++
            }
        }
    }
    val isCompleted = jugs.any { it == goal }
    LaunchedEffect(isCompleted) {
        if (isCompleted) timerStarted = false
    }
    val scope = rememberCoroutineScope()

    val animatedColor by animateColorAsState(
        targetValue = MaterialTheme.colorScheme.secondary,
        label = "hintColor"
    )
    fun startTimerIfNeeded() {
        if (!timerStarted) {
            timerStarted = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundSoft)
            .padding(horizontal = 16.dp)
    ) {

        // ---------- HEADER ----------
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Level $levelNumber", fontSize = 22.sp)

            Button(onClick = onBack, shape = RoundedCornerShape(18.dp)) {
                Text("Back")
            }
        }

        Spacer(Modifier.height(8.dp))

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                "Goal: $goal Litre${if (goal > 1) "s" else ""}",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Text("Moves: $moves")
            Text("Time: ${timeElapsed}s")

            AnimatedContent(targetState = lastAction, label = "") {
                Text(it, color = animatedColor)
            }
        }

        Spacer(Modifier.height(10.dp))
        HorizontalDivider()
        Spacer(Modifier.height(10.dp))

        // ---------- JUG DISPLAY ----------
        Box(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceEvenly) {
                capacities.forEachIndexed { index, cap ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        JugView(jugs[index], cap, MaterialTheme.colorScheme.secondary)
                        Text("${cap}L")
                    }
                }
            }
        }

        // ---------- CONTROLS ----------
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {

            // =========================
            // 2 JUG LEVELS
            // =========================
            if (capacities.size == 2) {

                item {
                    val capA = capacities[0]
                    val capB = capacities[1]

                    Row(Modifier.fillMaxWidth(), Arrangement.SpaceEvenly) {
                        Button({ startTimerIfNeeded()
                            jugs = listOf(capA, jugs[1]); if (!timerStarted && !isCompleted) timerStarted = true
                            if (!isCompleted) {
                                if (!timerStarted) timerStarted = true
                                if (!isCompleted) {
                                    if (!timerStarted) timerStarted = true
                                    moves++
                                }
                            } }) { Text("Fill A") }
                        Button({ startTimerIfNeeded()
                            jugs = listOf(jugs[0], capB); if (!timerStarted && !isCompleted) timerStarted = true
                            if (!isCompleted) {
                                if (!timerStarted) timerStarted = true
                                if (!isCompleted) {
                                    if (!timerStarted) timerStarted = true
                                    moves++
                                }
                            } }) { Text("Fill B") }
                    }

                    Row(Modifier.fillMaxWidth(), Arrangement.SpaceEvenly) {
                        Button({ startTimerIfNeeded()
                            jugs = listOf(0, jugs[1]); if (!timerStarted && !isCompleted) timerStarted = true
                            if (!isCompleted) {
                                if (!timerStarted) timerStarted = true
                                if (!isCompleted) {
                                    if (!timerStarted) timerStarted = true
                                    moves++
                                }
                            } }) { Text("Empty A") }
                        Button({startTimerIfNeeded()
                            jugs = listOf(jugs[0], 0); if (!timerStarted && !isCompleted) timerStarted = true
                            if (!isCompleted) {
                                if (!timerStarted) timerStarted = true
                                if (!isCompleted) {
                                    if (!timerStarted) timerStarted = true
                                    if (!isCompleted) {
                                        if (!timerStarted) timerStarted = true
                                        moves++
                                    }
                                }
                            } }) { Text("Empty B") }
                    }

                    Row(Modifier.fillMaxWidth(), Arrangement.SpaceEvenly) {
                        Button({ startTimerIfNeeded()
                            val t = minOf(jugs[0], capB - jugs[1])
                            jugs = listOf(jugs[0] - t, jugs[1] + t)
                            if (!timerStarted && !isCompleted) timerStarted = true
                            if (!isCompleted) {
                                if (!timerStarted) timerStarted = true
                                moves++
                            }
                        }) { Text("A → B") }

                        Button({ startTimerIfNeeded()
                            val t = minOf(jugs[1], capA - jugs[0])
                            jugs = listOf(jugs[0] + t, jugs[1] - t)
                            if (!timerStarted && !isCompleted) timerStarted = true
                            if (!isCompleted) {
                                if (!timerStarted) timerStarted = true
                                moves++
                            }
                        }) { Text("B → A") }
                    }
                }

                // ---------- Hint + AutoSolve (Same Row) ----------
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        Button(onClick = { startTimerIfNeeded()
                            usedHint = true
                            timeElapsed += 2   // ⏱ penalty
                            val path = WaterJugSolver.solve(
                                capacities[0], capacities[1], goal,
                                jugs[0], jugs[1]
                            )
                            lastAction =
                                if (path.isNotEmpty()) "Hint: ${path.first().action}"
                                else "No solution"
                        }) {
                            Text("Hint")
                        }

                        Button(onClick = { startTimerIfNeeded()

                            usedAutoSolve = true
                            lastAction = "Auto-solving… No score awarded."

                            scope.launch {
                                val path = WaterJugSolver.solve(
                                    capacities[0], capacities[1], goal,
                                    jugs[0], jugs[1]
                                )

                                for (move in path) {

                                    var a = jugs[0]
                                    var b = jugs[1]

                                    when (move.action) {
                                        "Fill A" -> a = capacities[0]
                                        "Fill B" -> b = capacities[1]
                                        "Empty A" -> a = 0
                                        "Empty B" -> b = 0
                                        "Pour A → B" -> {
                                            val t = minOf(a, capacities[1] - b)
                                            a -= t; b += t
                                        }
                                        "Pour B → A" -> {
                                            val t = minOf(b, capacities[0] - a)
                                            b -= t; a += t
                                        }
                                    }

                                    jugs = listOf(a, b)
                                    if (!timerStarted && !isCompleted) timerStarted = true
                                    if (!isCompleted) {
                                        if (!timerStarted) timerStarted = true
                                        moves++
                                    }
                                    if (a == goal || b == goal) break
                                    delay(350)
                                }
                            }

                        }) {
                            Text("Auto Solve")
                        }
                    }
                }

// ---------- Reset (Separate Row) ----------
                item {
                    Button(onClick = {
                        jugs = List(capacities.size) { 0 }
                        moves = 0
                        usedHint = false
                        usedAutoSolve = false
                        lastAction = "Reset"
                    }) {
                        Text("Reset")
                    }
                }


            }

            // =========================
            // 3+ JUG LEVELS  (RESTORED)
            // =========================
            else {

                // ---------- Fill Buttons ----------
                item {
                    Row(Modifier.fillMaxWidth(), Arrangement.SpaceEvenly) {
                        capacities.forEachIndexed { i, cap ->
                            Button({ startTimerIfNeeded()
                                jugs = jugs.toMutableList().also { it[i] = cap }
                                if (!timerStarted && !isCompleted) timerStarted = true
                                if (!isCompleted) {
                                    if (!timerStarted) timerStarted = true
                                    moves++
                                }
                                lastAction = "Fill ${i + 1}"
                            }) { Text("Fill ${i + 1}") }
                        }
                    }
                }

                // ---------- Empty Buttons ----------
                item {
                    Row(Modifier.fillMaxWidth(), Arrangement.SpaceEvenly) {
                        capacities.forEachIndexed { i, _ ->
                            Button({ startTimerIfNeeded()
                                jugs = jugs.toMutableList().also { it[i] = 0 }

                                if (!isCompleted) {
                                    if (!timerStarted) timerStarted = true
                                    moves++
                                }
                                lastAction = "Empty ${i + 1}"
                            }) { Text("Empty ${i + 1}") }
                        }
                    }
                }


                // ---------- Pour Buttons ----------
                pourPairs.forEach { row ->

                item {
                        Row(Modifier.fillMaxWidth(), Arrangement.SpaceEvenly) {
                            row.forEach { (from, to) ->
                                Button({ startTimerIfNeeded()
                                    val transfer = minOf(jugs[from], capacities[to] - jugs[to])
                                    val new = jugs.toMutableList()
                                    new[from] -= transfer
                                    new[to] += transfer
                                    jugs = new
                                    if (!timerStarted && !isCompleted) timerStarted = true
                                    if (!isCompleted) {
                                        if (!timerStarted) timerStarted = true
                                        moves++
                                    }
                                    lastAction = "Pour ${from + 1} → ${to + 1}"
                                }) { Text("${from + 1} → ${to + 1}") }
                            }
                        }
                    }
                }

                // ---------- Hint ----------
                // ---------- Hint + AutoSolve (Same Row) ----------
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        Button(onClick = { startTimerIfNeeded()
                            usedHint = true
                            timeElapsed += 2   // ⏱ penalty
                            val move = MultiJugHintSolver.getNextMove(capacities, jugs, goal)
                            lastAction = move?.description ?: "No hint available"
                        }) {
                            Text("Hint")
                        }

                        Button(onClick = { startTimerIfNeeded()

                            usedAutoSolve = true
                            lastAction = "Auto-solving… No score awarded."

                            scope.launch {

                                val solution = com.example.waterjuggame.MultiJugSolver.solve(capacities, goal)

                                if (solution.isEmpty()) {
                                    lastAction = "No solution exists."
                                    timerStarted = false
                                    return@launch
                                }

                                for (step in solution) {

                                    jugs = step.to.amounts   // jump to BFS state
                                    moves++

                                    lastAction = step.action

                                    if (jugs.any { it == goal }) break

                                    delay(350)
                                }
                            }

                        }) {
                            Text("Auto Solve")
                        }
                    }
                }

// ---------- Reset (Separate Row) ----------
                item {
                    Button(onClick = {
                        jugs = List(capacities.size) { 0 }
                        moves = 0
                        usedHint = false
                        usedAutoSolve = false
                        lastAction = "Reset"
                    }) {
                        Text("Reset")
                    }
                }

            }


            // =========================
            // COMPLETION
            // =========================
            if (isCompleted) {

                val levelScore =
                    if (usedAutoSolve) 0
                    else {
                        val movePenalty = moves * 2
                        val hintPenalty = if (usedHint) 15 else 0
                        val timePenalty = timeElapsed  // 1 point per second

                        (120 - movePenalty - hintPenalty - timePenalty)
                            .coerceAtLeast(5)
                    }
                item {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Text("🎉 Level Completed!", style = MaterialTheme.typography.titleLarge)
                        Spacer(Modifier.height(6.dp))
                        Text("You earned $levelScore points")

                        Spacer(Modifier.height(10.dp))

                        Button(onClick = {
                            UserManager.incrementLevelsCompleted(context)
                            UserManager.updateBestScore(context, levelNumber, levelScore)
                            onLevelCompleted()
                        }) {
                            Text("Next Level ▶")
                        }
                    }
                }
            }
        }
    }
}
