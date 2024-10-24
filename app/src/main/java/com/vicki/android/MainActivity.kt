package com.vicki.android

import android.os.Bundle
import android.util.Log
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
import androidx.lifecycle.lifecycleScope
import com.google.gson.GsonBuilder
import com.vicki.android.api.BlogApi
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    private val blogList = listOf<Blog>(Blog("one", "one body"), Blog("two", "two body"),Blog("one", "one body"), Blog("two", "two body"),Blog("one", "one body"), Blog("two", "two body"),Blog("one", "one body"), Blog("two", "two body"),Blog("one", "one body"), Blog("two", "two body"),Blog("one", "one body"), Blog("two", "two body"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://catfact.ninja/fact/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val blogApi: BlogApi = retrofit.create()


        asynchronousGetRequest("https://catfact.ninja/fact/")

        this.lifecycleScope.launch {
            val responed = blogApi.fetchBlog().toResponseBody()
            Log.d(TAG, "response received from retrofit $responed")

        }

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

 fun asynchronousGetRequest(url: String) {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: java.io.IOException) {
                // Handle failure
            }

            override fun onResponse(call: Call, response: Response) {
                // Handle success
                val result = response.body?.string() ?: ""
                Log.d(TAG, result)
            }
        })
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

