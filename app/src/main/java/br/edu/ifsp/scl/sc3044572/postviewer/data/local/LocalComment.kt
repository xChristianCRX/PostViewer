package br.edu.ifsp.scl.sc3044572.postviewer.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.edu.ifsp.scl.sc3044572.postviewer.model.Comment

@Entity(tableName = "local_comments")
data class LocalComment(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val postId: Int,
    val name: String,
    val email: String,
    val body: String
) {
    fun toComment() = Comment(
        postId = postId,
        id = id,
        name = name,
        email = email,
        body = body,
        isLocal = true
    )
}