package com.alnod.projectx.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alnod.projectx.ui.screens.auth.LoginScreen
import com.alnod.projectx.ui.screens.auth.RegisterScreen
import com.alnod.projectx.ui.screens.home.HomeScreen
import com.alnod.projectx.ui.screens.splash.SplashScreen
import com.alnod.projectx.ui.screens.start.StartScreen


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_HOME
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
            HomeScreen(navController = navController)
        }
        composable(ROUT_START){
            StartScreen(navController = navController)
        }
    }
}
