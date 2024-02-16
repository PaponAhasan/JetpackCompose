package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class ThemeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun App() {
    val theme = remember {
        mutableStateOf(false)
    }

    JetpackComposeTheme(theme.value) {
        // A surface container using the 'background' color from the theme
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            Text(text = "MY THEME COLOR")
            Button(onClick = {
                theme.value = !theme.value
            }) {
                Text(text = "CHANGE THEME")
            }
        }
    }

}