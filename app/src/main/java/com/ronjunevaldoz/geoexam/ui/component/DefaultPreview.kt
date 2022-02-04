package com.ronjunevaldoz.geoexam.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ronjunevaldoz.geoexam.ui.theme.GeoExamTheme

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GeoExamTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Navigation()
        }
    }
}