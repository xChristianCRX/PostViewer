package br.edu.ifsp.scl.sc3044572.postviewer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.scl.sc3044572.postviewer.data.PostRepository
import br.edu.ifsp.scl.sc3044572.postviewer.data.local.AppDatabase
import br.edu.ifsp.scl.sc3044572.postviewer.data.local.LocalComment
import br.edu.ifsp.scl.sc3044572.postviewer.data.remote.PostApi
import br.edu.ifsp.scl.sc3044572.postviewer.model.Comment
import br.edu.ifsp.scl.sc3044572.postviewer.model.Post
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostViewModel(application: Application): AndroidViewModel(application) {
    private val api = Retrofit.Builder()
        .baseUrl(PostApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PostApi::class.java)

    private val dao = AppDatabase.getDatabase(application).commentDao()
    private val repository = PostRepository(api, dao)

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts.asStateFlow()

    private val _postComments = MutableStateFlow<List<Comment>>(emptyList())
    val postComments: StateFlow<List<Comment>> = _postComments.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private var commentsJob: Job? = null

    init {
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _posts.value = repository.getPosts()
            } catch (e: Exception) {
                _posts.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadCommentsForPost(postId: Int) {
        commentsJob?.cancel()
        commentsJob = viewModelScope.launch {
            _isLoading.value = true
            try {
                val remoteComments = try {
                    repository.getRemoteComments(postId)
                } catch (e: Exception) {
                    emptyList()
                }

                repository.getLocalComments(postId).collect { localComments ->
                    _postComments.value = remoteComments + localComments
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _postComments.value = emptyList()
                _isLoading.value = false
            }
        }
    }

    fun addLocalComment(postId: Int, body: String) {
        viewModelScope.launch {
            val localComment = LocalComment(
                postId = postId,
                name = "Você",
                email = "voce@local.com",
                body = body
            )
            repository.addLocalComment(localComment)
        }
    }
}
