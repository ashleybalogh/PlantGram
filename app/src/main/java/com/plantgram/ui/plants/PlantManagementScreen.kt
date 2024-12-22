package com.plantgram.ui.plants

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PlantManagementScreen() {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Plant Management") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Plant Management Screen (Placeholder)",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}