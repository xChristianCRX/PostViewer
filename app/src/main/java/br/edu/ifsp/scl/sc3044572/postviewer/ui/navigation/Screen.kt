package br.edu.ifsp.scl.sc3044572.postviewer.ui.navigation

sealed class Screen(val route: String) {
    object List : Screen("post_list")
    object Details : Screen("post_detail/{postId}") {
        fun createRoute(postId: Int) = "post_detail/$postId"
    }
}