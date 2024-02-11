package com.example.jetpackcompose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.R

@Preview(heightDp = 500)
@Composable
fun PreviewItem() {
    /*Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        getCategoryList().map {item ->
            BlogCategory(
                item.img,
                item.title,
                item.subTitle
            )
        }
    }*/

    LazyColumn(content = {
        items(getCategoryList()){ item ->
            BlogCategory(
                item.img,
                item.title,
                item.subTitle
            )
        }
    })
}

@Composable
fun BlogCategory(imageId: Int, name: String, occupation: String) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier.padding(
            start = 12.dp,
            top = 10.dp,
            bottom = 10.dp,
            end = 12.dp
        )

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                top = 10.dp,
                bottom = 10.dp)
        ) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp)
                    .weight(.2f)
            )
            ItemDescription(name, occupation, Modifier.weight(.8f))
        }
    }
}

@Composable
private fun ItemDescription(name: String, occupation: String, modifier: Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = name,
            //fontSize = 16.sp,
            //fontWeight = FontWeight.Bold
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = occupation,
            //fontSize = 14.sp,
            //color = Color.Gray,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Thin
        )
    }
}

data class Category(val img : Int, val title : String, val subTitle : String)

fun getCategoryList(): MutableList<Category> {
    val list = mutableListOf<Category>()
    list.add(Category(R.drawable.ic_360, "Karim", "Laravel Developer"))
    list.add(Category(R.drawable.ic_airplane, "Rahim", "Android Developer"))
    list.add(Category(androidx.core.R.drawable.ic_call_decline, "Jarin", "Data Developer"))
    list.add(Category(androidx.core.R.drawable.ic_call_answer_video, "Hanif", "DotNet Developer"))
    list.add(Category(androidx.core.R.drawable.ic_call_answer, "Papon", "App Developer"))
    list.add(Category(androidx.core.R.drawable.ic_call_decline, "Jarin", "Data Developer"))
    list.add(Category(androidx.core.R.drawable.ic_call_answer_video, "Hanif", "DotNet Developer"))
    list.add(Category(androidx.core.R.drawable.ic_call_answer, "Papon", "App Developer"))
    list.add(Category(androidx.core.R.drawable.ic_call_answer_video, "Hanif", "DotNet Developer"))
    list.add(Category(androidx.core.R.drawable.ic_call_answer, "Papon", "App Developer"))
    list.add(Category(androidx.core.R.drawable.ic_call_decline, "Jarin", "Data Developer"))
    list.add(Category(androidx.core.R.drawable.ic_call_answer_video, "Hanif", "DotNet Developer"))
    list.add(Category(androidx.core.R.drawable.ic_call_answer, "Papon", "App Developer"))

    return list
}