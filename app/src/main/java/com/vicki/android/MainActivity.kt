package com.vicki.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    private val blogList = listOf<Blog>(Blog("one", "one body"), Blog("two", "two body"),Blog("one", "one body"), Blog("two", "two body"),Blog("one", "one body"), Blog("two", "two body"),Blog("one", "one body"), Blog("two", "two body"),Blog("one", "one body"), Blog("two", "two body"),Blog("one", "one body"), Blog("two", "two body"))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                item() {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "list of questions ",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                items(blogList) {
                    BlogItem(it)
                }
            }
        }
    }
}

@Composable
fun BlogItem(blog: Blog, modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Text(text = blog.title)
    }
    Row(
        modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Text(text = blog.body)
    }
}
