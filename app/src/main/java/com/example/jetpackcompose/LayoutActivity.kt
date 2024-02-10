package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class LayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        }
    }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 300)
@Composable
fun LayoutPreview() {
    /* --------- Column -----------*/
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "A", fontSize = 14.sp)
        Text(text = "B", fontSize = 14.sp)
    }

    /* --------- Row -----------*/
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "C", fontSize = 14.sp)
        Text(text = "D", fontSize = 14.sp)
    }

    /* --------- Box -----------*/
    Box(
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_airplane),
            contentDescription = "",
            colorFilter = ColorFilter.tint(Color.Red)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_360),
            contentDescription = "",
            colorFilter = ColorFilter.tint(Color.Green)
        )
    }
}

/* --------- Example Layout -----------*/
@Composable
fun ListViewItem(imageId: Int, name: String, occupation: String) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = "",
            modifier = Modifier.size(width = 50.dp, height = 50.dp)
        )
        Column(
            modifier = Modifier.padding(start = 5.dp)
        ) {
            Text(
                text = name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = occupation,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 300)
@Composable
private fun PreviewFunction() {
    Column {
        ListViewItem(
            R.drawable.ic_account_circle,
            "Papon Ahasan",
            "Android Developer"
        )
        ListViewItem(
            R.drawable.ic_account_circle,
            "Rakibul Ahasan",
            "Android Developer"
        )
    }
}