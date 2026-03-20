package com.alnod.projectx.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alnod.projectx.R
import com.alnod.projectx.navigation.ROUT_HOME
import com.alnod.projectx.navigation.ROUT_LOGIN
import com.alnod.projectx.navigation.ROUT_START
import com.alnod.projectx.ui.screens.components.OrDivider
import com.alnod.projectx.ui.screens.components.SocialButton
import com.alnod.projectx.ui.theme.blue
import com.alnod.projectx.ui.theme.purple
import com.alnod.projectx.viewmodels.AuthViewModel

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val user by viewModel.user.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val context = LocalContext.current

    // Navigate to HOME when user is signed up
    LaunchedEffect(user) {
        if (user != null) {
            navController.navigate(ROUT_HOME) {
                popUpTo(0)
            }
        }
    }

    // Show errors
    LaunchedEffect(error) {
        error?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            viewModel.clearError()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.horizontalGradient(colors = listOf(blue, purple)))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.navigate(ROUT_START) }) {
                    Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = null, tint = Color.White)
                }
                Spacer(modifier = Modifier.width(35.dp))
                Text(text = "Already have an account?", color = Color.White)
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = { navController.navigate(ROUT_LOGIN) },
                    modifier = Modifier.height(50.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "Sign In")
                }
            }

            Spacer(modifier = Modifier.height(70.dp))
            Text(text = "Homie", color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
            Spacer(modifier = Modifier.height(60.dp))

            Card(
                modifier = Modifier.fillMaxSize(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(14.dp).fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Get Started", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email Address") },
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp)
                    )
                    Spacer(modifier = Modifier.height(15.dp))

                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("User Name") },
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp)
                    )
                    Spacer(modifier = Modifier.height(15.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Enter Password") },
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val icon = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(icon, contentDescription = "Toggle Password")
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(15.dp))

                    Button(
                        onClick = { viewModel.signUp(email, password) },
                        modifier = Modifier.width(330.dp).height(70.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        enabled = !isLoading
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    brush = Brush.verticalGradient(colors = listOf(blue, purple)),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            if (isLoading) {
                                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                            } else {
                                Text(text = "Sign up", color = Color.White, fontSize = 15.sp)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(25.dp))
                    OrDivider(text = "Or sign up with")
                    Spacer(modifier = Modifier.height(20.dp))
                    SocialButton(text = "Google", iconRes = R.drawable.google_logo, onClick = {})
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(navController = rememberNavController())
}
