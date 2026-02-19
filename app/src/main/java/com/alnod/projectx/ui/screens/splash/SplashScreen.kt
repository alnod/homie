package com.alnod.projectx.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alnod.projectx.R
import com.alnod.projectx.navigation.ROUT_REGISTER
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController

){

    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate(ROUT_REGISTER)

    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.splash),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }

}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    SplashScreen(navController = rememberNavController())
}