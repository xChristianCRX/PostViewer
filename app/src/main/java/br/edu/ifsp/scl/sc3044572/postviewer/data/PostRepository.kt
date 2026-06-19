package br.edu.ifsp.scl.sc3044572.postviewer.data

import br.edu.ifsp.scl.sc3044572.postviewer.data.local.CommentDao
import br.edu.ifsp.scl.sc3044572.postviewer.data.local.LocalComment
import br.edu.ifsp.scl.sc3044572.postviewer.data.remote.PostApi
import br.edu.ifsp.scl.sc3044572.postviewer.model.Comment
import br.edu.ifsp.scl.sc3044572.postviewer.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class PostRepository(
    private val api: PostApi,
    private val dao: CommentDao
) {
    suspend fun getPosts(): List<Post> {
        val posts = api.getPosts()
        return posts.onEach {
            post -> post.quantityComments =  api.getComments(post.id).size
        }
    }

    suspend fun getRemoteComments(postId: Int): List<Comment> = api.getComments(postId)

    fun getLocalComments(postId: Int): Flow<List<Comment>> {
        return dao.getLocalComments(postId).map { localList ->
            localList.map { it.toComment() }
        }
    }

    suspend fun addLocalComment(comment: LocalComment) {
        dao.insertComment(comment)
    }
}