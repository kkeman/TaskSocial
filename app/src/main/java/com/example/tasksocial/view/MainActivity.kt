package com.example.tasksocial.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.tasksocial.common.Constant
import com.example.tasksocial.model.Ad
import com.example.tasksocial.model.Comment
import com.example.tasksocial.model.Feed
import com.example.tasksocial.ui.theme.TaskSocialTheme
import com.example.tasksocial.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            TaskSocialTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "Home") {
                        composable("Home") {
                            Home(viewModel = viewModel, navController = navController)
                        }
                        composable("Detail/{index}") {
                            Detail(viewModel = viewModel, navBackStackEntry  = it) }
                    }
                }
            }
        }
        viewModel.loadMainModels()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IconToggleButton()
}

@Composable
fun Home(viewModel: MainViewModel, onItemClick: (Int) -> Unit = {}, navController: NavController) {
    val feedLists: List<Any> = viewModel.allList.value!!
    LazyColumn {
        itemsIndexed(feedLists) { index, item ->
            if (item is Feed)
                FeedItem(feed = item, index = index, onItemClick = onItemClick ,navController = navController)
            else
                AdItem(ad = item as Ad)

            Divider(thickness = 5.dp, color = Color.Gray)
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun FeedItem(feed: Feed, index: Int, onItemClick: (Int) -> Unit = {}, navController: NavController) {
    Column(modifier = Modifier.clickable {
//        onItemClick.invoke(index)
//        navController.navigate("Detail")
        navController.navigate("Detail/$index")
    }) {
        if (feed.type == Constant.TYPE_A)
            Image(
                painter = rememberAsyncImagePainter(feed.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                contentScale = ContentScale.FillWidth,
            )

        Text(
            text = feed.description,
            style = typography.bodyLarge,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconToggleButton()
            Text(
                text = "LIKE " + feed.likecount.toString(),
                style = typography.bodyLarge,
            )
        }

        if (feed.comments?.isNotEmpty() == true) {
            var comment1 by remember { mutableStateOf("") }
            if (comment1.isNotEmpty())
                MessageText(comment1, 1)
            var comment2 by remember { mutableStateOf("") }
            if (comment2.isNotEmpty())
                MessageText(comment2, 1)

            rememberCoroutineScope().launch {
                comment1 = textComment(feed.comments[0])
                if (feed.comments.size > 1)
                    comment2 = textComment(feed.comments[1])
            }
        }
    }
}

@Composable
fun AdItem(ad: Ad) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = Color.Cyan)
    ) {
        Column {
            Text(
                text = ad.title,
                style = typography.bodyLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun IconToggleButton() {
    var checked by remember { mutableStateOf(false) }
    IconToggleButton(
        checked = checked,
        onCheckedChange = { checked = it },
        modifier = Modifier.size(70.dp),
    ) {
        val tint by animateColorAsState(
            if (checked) Color.Red
            else Color.LightGray
        )
        Icon(
            Icons.Filled.Favorite,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
fun MessageText(text: String, maxLines: Int) {
    Text(
        text = text,
        style = typography.bodyLarge,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
    )
}

fun textComment(comment: Comment): String {
    return "*" + comment.userName + " : " + comment.message
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Detail(viewModel: MainViewModel, navBackStackEntry: NavBackStackEntry) {
    val index = navBackStackEntry.arguments?.getString("index")
    val feed = viewModel.allList.value!![index!!.toInt()] as Feed

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 50.dp)
    ) {
        if (feed.type == Constant.TYPE_A)
            Image(
                painter = rememberAsyncImagePainter(feed.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                contentScale = ContentScale.FillWidth,
            )

        Text(
            text = feed.description,
            style = typography.bodyLarge,
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconToggleButton()
            Text(
                text = "LIKE " + feed.likecount.toString(),
                style = typography.bodyLarge,
            )
        }

        if (feed.comments?.isNotEmpty() == true) {
            for (item in feed.comments) {
                var comment by remember { mutableStateOf("") }
                if (comment.isNotEmpty())
                    MessageText(comment, Int.MAX_VALUE)

                rememberCoroutineScope().launch {
                    comment = textComment(item)
                }
            }
        }
    }
    Row(
        modifier = Modifier.fillMaxHeight(),
        verticalAlignment = Alignment.Bottom
    ) {
        var text by remember { mutableStateOf(TextFieldValue("")) }
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            placeholder = { Text(text = "LGTM!") },
            modifier = Modifier
                .weight(9f)
                .height(50.dp)
        )
        Button(
            onClick = { },
            modifier = Modifier.width(100.dp)
        ) {
            Text(text = "SEND")
        }
    }
}