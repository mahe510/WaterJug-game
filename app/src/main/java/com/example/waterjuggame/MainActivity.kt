package com.example.waterjuggame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.SideEffect

import com.google.accompanist.systemuicontroller.rememberSystemUiController

import com.example.waterjuggame.data.LevelRepository
import com.example.waterjuggame.data.ProgressManager
import com.example.waterjuggame.data.UserManager
import com.example.waterjuggame.ui.GameScreen
import com.example.waterjuggame.ui.LevelSelectScreen
import com.example.waterjuggame.ui.LoginScreen
import com.example.waterjuggame.ui.theme.WaterJugTheme
import com.example.waterjuggame.ui.theme.BackgroundSoft

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            WaterJugTheme {

                // ✅ Set system bar color
                val systemUiController = rememberSystemUiController()

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = BackgroundSoft,
                        darkIcons = true
                    )
                }

                val context = LocalContext.current

                // ✅ Check if user already logged in
                var isLoggedIn by remember {
                    mutableStateOf(UserManager.getCurrentUser(context) != null)
                }

                // ---------- LOGIN FLOW ----------
                if (!isLoggedIn) {

                    LoginScreen(
                        onLoginSuccess = {
                            isLoggedIn = true
                        }
                    )

                } else {

                    // ---------- YOUR EXISTING APP FLOW ----------
                    var currentScreen by remember { mutableStateOf("menu") }
                    var selectedLevelIndex by remember { mutableStateOf(0) }
                    var refreshTrigger by remember { mutableStateOf(0) }

                    if (currentScreen == "menu") {

                        LevelSelectScreen(
                            refreshTrigger = refreshTrigger
                        ) { index ->
                            selectedLevelIndex = index
                            currentScreen = "game"
                        }

                    } else {

                        GameScreen(
                            levelNumber = selectedLevelIndex + 1,
                            level = LevelRepository.levels[selectedLevelIndex],
                            onBack = { currentScreen = "menu" },
                            onLevelCompleted = {
                                ProgressManager.unlockNext(context, selectedLevelIndex)
                                refreshTrigger++
                                currentScreen = "menu"
                            }
                        )

                    }
                }
            }
        }
    }
}
