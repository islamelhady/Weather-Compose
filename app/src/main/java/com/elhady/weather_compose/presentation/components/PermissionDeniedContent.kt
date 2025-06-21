package com.elhady.weather_compose.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PermissionDeniedContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Location permission is required to show the weather.", textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(height = 16.dp))
        Button(onClick = { /* TODO: Open app settings */ }) {
            Text("Grant Permission")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrePreview(){
    PermissionDeniedContent()
}