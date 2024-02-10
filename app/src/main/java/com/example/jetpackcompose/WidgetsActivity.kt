package com.example.jetpackcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

class WidgetsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextInput()
        }
    }
}

/* ------------- Text ------------- */
@Composable
fun SayYourName(name: String) {
    Text(
        text = "My Name : $name",
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Bold,
        color = Color.Red,
        fontSize = 24.sp,
    )
}

@Preview(showBackground = true, name = "SayName", showSystemUi = true)
@Composable
private fun SayYourNamePreview() {
    SayYourName(name = "Papon")
}

/* ------------- Image ------------- */
@Preview(showBackground = true, name = "showImage", widthDp = 200, heightDp = 200)
@Composable
fun ImageShowFunction() {
    Image(
        painter = painterResource(id = R.drawable.ic_360),
        contentDescription = "myImage",
        colorFilter = ColorFilter.tint(Color.Red),
        contentScale = ContentScale.Crop
    )
}

/* ------------- Button ------------- */
@Preview(showBackground = true, name = "buttonClick", widthDp = 200, heightDp = 300)
@Composable
private fun ButtonClickFun() {
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = Color.Black
        ),
        enabled = false
    ) {
        Text(text = "Hello")
        Image(
            painter = painterResource(
                id = R.drawable.ic_360
            ),
            contentDescription = "myImage"
        )
    }
}

/* ------------- TextField ------------- */

@Preview(showBackground = true, name = "textField", widthDp = 200, heightDp = 300)
@Composable
private fun TextFieldFun() {
    TextField(
        value = "Hello Papon",
        onValueChange = {},
        label = { Text(text = "Enter Message") },
        placeholder = { Text(text = "Message...") }
    )
}

@Composable
fun TextInput() {
    val state = remember {
        mutableStateOf("")
    }
    TextField(
        value = state.value,
        onValueChange = {
            Log.d("TextField", it)
            state.value = it
        },
        label = { Text(text = "Enter Message") },
    )
}