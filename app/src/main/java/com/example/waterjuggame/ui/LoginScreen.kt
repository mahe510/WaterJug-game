package com.example.waterjuggame.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.waterjuggame.data.User


import com.example.waterjuggame.data.UserManager
import com.example.waterjuggame.ui.theme.BackgroundSoft

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {

    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Surface(   // <-- forces theme surface instead of black window
        modifier = Modifier.fillMaxSize(),
        color = BackgroundSoft
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                "Water Jug Game",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(Modifier.height(24.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                singleLine = true
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                singleLine = true
            )

            Spacer(Modifier.height(24.dp))

            Button(onClick = {
                if (name.isNotBlank() && email.isNotBlank()) {
                    UserManager.login(context, name, email)

                    onLoginSuccess()
                }
            }) {
                Text("Start Playing")
            }
        }
    }
}
