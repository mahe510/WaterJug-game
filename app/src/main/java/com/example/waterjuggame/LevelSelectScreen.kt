package com.example.waterjuggame.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.waterjuggame.data.Difficulty
import com.example.waterjuggame.data.LevelRepository
import com.example.waterjuggame.data.ProgressManager
import com.example.waterjuggame.data.UserManager

@Composable
fun LevelSelectScreen(
    refreshTrigger: Int,
    onLevelSelected: (Int) -> Unit
) {

    val context = LocalContext.current

    // 🔥 re-read user when coming back from GameScreen
    val currentUser = remember(refreshTrigger) {
        UserManager.getCurrentUser(context)
    }

    val totalScore = remember(refreshTrigger) {
        UserManager.getTotalScore(context)
    }

    val levels = LevelRepository.levels

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ---------- USER DASHBOARD ----------
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column {
                        Text(
                            text = currentUser?.name ?: "Guest",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = "Score: $totalScore",
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Text(
                            text = "Levels Completed: ${currentUser?.levelsCompleted ?: 0}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Button(onClick = {
                        UserManager.logout(context)
                        (context as android.app.Activity).recreate()
                    }) {
                        Text("Home")
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text("Select Level", fontSize = 26.sp)

            Spacer(modifier = Modifier.height(10.dp))

            // ---------- LEVEL LIST ----------
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                var lastDifficulty: Difficulty? = null

                levels.forEachIndexed { index, level ->

                    // 🔹 Insert header WHEN difficulty changes
                    if (level.difficulty != lastDifficulty) {

                        lastDifficulty = level.difficulty

                        item {
                            Text(
                                text = when (level.difficulty) {
                                    Difficulty.EASY -> "Easy Levels"
                                    Difficulty.MEDIUM -> "Medium Levels"
                                    Difficulty.HARD -> "Hard Levels"
                                },
                                style = MaterialTheme.typography.titleMedium,
                                fontSize = 20.sp,
                                modifier = Modifier.padding(top = 12.dp, bottom = 4.dp)
                            )
                        }
                    }

                    // 🔹 Each level must be inside item{}
                    item {

                        val unlocked = ProgressManager.isUnlocked(context, index)

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(70.dp)
                                .clickable(enabled = unlocked) {
                                    if (unlocked) onLevelSelected(index)
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = if (unlocked)
                                    MaterialTheme.colorScheme.surface
                                else
                                    MaterialTheme.colorScheme.surfaceVariant
                            ),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {

                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                val bestScore = UserManager.getLevelBestScore(context, level.id)

                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(horizontal = 20.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    // Left → Level Name
                                    Text(
                                        text = if (unlocked)
                                            "Level ${level.id}"
                                        else
                                            "🔒 Level ${level.id}",
                                        fontSize = 18.sp
                                    )

                                    // Right → Best Score (only if played)
                                    if (bestScore > 0) {
                                        Text(
                                            text = "⭐ $bestScore",
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}