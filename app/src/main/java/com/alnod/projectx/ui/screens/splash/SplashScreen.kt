package com.alnod.projectx.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alnod.projectx.R
import com.alnod.projectx.navigation.ROUT_HOME
import com.alnod.projectx.navigation.ROUT_SPLASH
import com.alnod.projectx.navigation.ROUT_START
import com.alnod.projectx.viewmodels.AuthViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
) {
    val user by viewModel.user.collectAsState()

    LaunchedEffect(Unit) {
        delay(3000)
        if (user != null) {
            navController.navigate(ROUT_HOME) {
                popUpTo(ROUT_SPLASH) { inclusive = true }
            }
        } else {
            navController.navigate(ROUT_START) {
                popUpTo(ROUT_SPLASH) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.splash),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController())
}
