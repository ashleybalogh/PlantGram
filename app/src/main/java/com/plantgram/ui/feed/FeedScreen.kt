package com.plantgram.ui.feed

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FeedScreen(
    onNavigateToProfile: (String) -> Unit,
    onNavigateToPlantDetail: (String) -> Unit
) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("PlantGram Feed") }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(3) { index ->
                PlaceholderPost(
                    onProfileClick = { onNavigateToProfile("user$index") },
                    onPlantClick = { onNavigateToPlantDetail("plant$index") }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun PlaceholderPost(
    onProfileClick: () -> Unit,
    onPlantClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            TextButton(onClick = onProfileClick) {
                Text("User Profile")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                TextButton(
                    onClick = onPlantClick,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text("View Plant Details")
                }
            }
        }
    }
}