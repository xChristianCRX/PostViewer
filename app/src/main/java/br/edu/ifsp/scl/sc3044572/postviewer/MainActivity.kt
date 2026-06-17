package br.edu.ifsp.scl.sc3044572.postviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.edu.ifsp.scl.sc3044572.postviewer.ui.composable.MainNavHost
import br.edu.ifsp.scl.sc3044572.postviewer.ui.composable.MainTopAppBar
import br.edu.ifsp.scl.sc3044572.postviewer.ui.navigation.Screen
import br.edu.ifsp.scl.sc3044572.postviewer.ui.theme.PostViewerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val mainNavHostController: NavHostController = rememberNavController()

            val navBackStackEntry by mainNavHostController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            val postViewModel: PostViewModel = viewModel()

            PostViewerTheme {
                Scaffold(
                    topBar = {
                        val isListScreen = currentRoute == Screen.List.route

                        MainTopAppBar(
                            title = if (isListScreen) "Posts" else "Detalhes do Post",
                            showBackButton = !isListScreen,
                            onBackClick = { mainNavHostController.navigateUp() }
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    MainNavHost(
                        mainNavHostController = mainNavHostController,
                        postViewModel = postViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}