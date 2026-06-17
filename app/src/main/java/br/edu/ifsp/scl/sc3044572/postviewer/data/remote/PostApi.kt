package br.edu.ifsp.scl.sc3044572.postviewer.data.remote

import br.edu.ifsp.scl.sc3044572.postviewer.model.Comment
import br.edu.ifsp.scl.sc3044572.postviewer.model.Post
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApi {
    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("posts/{id}/comments")
    suspend fun getComments(@Path("id") postId: Int): List<Comment>

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}