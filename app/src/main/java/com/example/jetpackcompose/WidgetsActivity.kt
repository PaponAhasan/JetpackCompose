package com.example.jetpackcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun SayYourName(name: String, modifier: Modifier) {
    Text(
        text = "My Name : $name",
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        fontSize = 24.sp,
        modifier = modifier
            .background(Color.Cyan)
            .size(200.dp)
            .padding(20.dp)
            .border(4.dp, Color.Gray)
            .clip(CircleShape)
            .background(Color.Yellow)
            .clickable { }
    )
}

@Preview(showBackground = true, name = "SayName", showSystemUi = true)
@Composable
private fun SayYourNamePreview() {
    SayYourName(name = "Papon", modifier = Modifier)
}

/* ------------- Image ------------- */
@Preview(showBackground = true, name = "showImage", widthDp = 200, heightDp = 200)
@Composable
fun ImageShowFunction() {
    Image(
        painter = painterResource(id = R.drawable.ic_360),
        contentDescription = "myImage",
        colorFilter = ColorFilter.tint(Color.Red),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(80.dp)
            .background(Color.Gray)
            .clip(CircleShape)
            .border(2.dp, Color.LightGray, CircleShape)
            .shadow(elevation = 20.dp)
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

@Preview
@Composable
fun NotificationScreen() {
    val count = rememberSaveable {
        mutableIntStateOf(0)
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize(1f)
            .background(Color.White)
    ) {
        NotificationCounter(count.intValue) { count.intValue++ }
        MessageBar(count.intValue)
    }
}

@Composable
fun MessageBar(count: Int) {
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)) {
        Row(
            Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = Icons.Outlined.Favorite, contentDescription = "",
                Modifier.padding(4.dp)
            )
            Text(text = "Messages sent so far - $count")
        }
    }
}

@Composable
private fun NotificationCounter(count: Int, increment: () -> Unit) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = "You have sent $count notification")
        Button(
            onClick = {
                increment()
            },
        ) {
            Text(text = "Send Notification")
        }
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