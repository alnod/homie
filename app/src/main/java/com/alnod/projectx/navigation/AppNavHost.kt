package com.alnod.projectx.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alnod.projectx.ui.screens.auth.LoginScreen
import com.alnod.projectx.ui.screens.auth.RegisterScreen
import com.alnod.projectx.ui.screens.chat.ChatDetailScreen
import com.alnod.projectx.ui.screens.chat.ChatScreen
import com.alnod.projectx.ui.screens.home.HomeScreen
import com.alnod.projectx.ui.screens.post.AddPostScreen
import com.alnod.projectx.ui.screens.profile.ProfileScreen
import com.alnod.projectx.ui.screens.splash.SplashScreen
import com.alnod.projectx.ui.screens.start.StartScreen


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH
) {


    NavHost(navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_SPLASH){
            SplashScreen(navController = navController)
        }
        composable(ROUT_LOGIN){
            LoginScreen(navController = navController)
        }
        composable(ROUT_REGISTER){
            RegisterScreen(navController = navController)
        }
        composable(ROUT_HOME){
            HomeScreen(navController = navController,
                onProfileClick = { navController.navigate(ROUT_PROFILE) })
        }
        composable(ROUT_START){
            StartScreen(navController = navController)
        }
        composable(ROUT_CHAT){
            ChatScreen(navController = navController)
        }
        composable(
            route = "$ROUT_CHAT_DETAIL/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0
            ChatDetailScreen(navController = navController, userId = userId)
        }
        composable(ROUT_PROFILE){
            ProfileScreen(navController = navController)
        }
        composable(ROUT_ADD_POST){
            AddPostScreen(navController = navController)
        }
    }
}
