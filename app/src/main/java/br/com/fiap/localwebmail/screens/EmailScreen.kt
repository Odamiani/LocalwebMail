package br.com.fiap.localwebmail.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import br.com.fiap.localwebmail.model.emails

@Composable
fun EmailScreen(navController: NavController) {


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("emailpage") }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Novo E-mail")
            }
        }
    )
    { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            items(emails) { email ->
                EmailItem(email, navController)
            }
        }
    }
}

