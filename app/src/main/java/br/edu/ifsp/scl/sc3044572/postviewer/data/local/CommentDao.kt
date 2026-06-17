package br.edu.ifsp.scl.sc3044572.postviewer.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {
    @Insert
    suspend fun insertComment(comment: LocalComment)

    @Query("SELECT * FROM local_comments WHERE postId = :postId")
    fun getLocalComments(postId: Int): Flow<List<LocalComment>>
}