package br.edu.ifsp.scl.sc3044572.postviewer.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.edu.ifsp.scl.sc3044572.postviewer.PostViewModel
import br.edu.ifsp.scl.sc3044572.postviewer.ui.navigation.Screen

@Composable
fun MainNavHost(
    mainNavHostController: NavHostController,
    postViewModel: PostViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = mainNavHostController,
        startDestination = Screen.List.route,
        modifier = modifier
    ) {
        composable(
            route = Screen.List.route
        ) {
            PostListScreen(
                viewModel = postViewModel,
                onPostClick = { postId ->
                    mainNavHostController.navigate(Screen.Details.createRoute(postId))
                }
            )
        }

        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument("postId") { type = NavType.IntType })
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getInt("postId") ?: return@composable
            PostDetailScreen(
                postId = postId,
                viewModel = postViewModel
            )
        }
    }
}