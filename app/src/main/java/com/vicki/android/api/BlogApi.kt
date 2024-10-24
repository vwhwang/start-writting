package com.vicki.android.api

import com.vicki.android.ui.theme.Cat
import retrofit2.http.GET

interface BlogApi {
    @GET("/")
    suspend fun fetchBlog() : String
}