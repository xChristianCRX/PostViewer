package br.edu.ifsp.scl.sc3044572.postviewer.model

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    var quantityComments: Int
)

data class Comment(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String,
    val isLocal: Boolean = false
)