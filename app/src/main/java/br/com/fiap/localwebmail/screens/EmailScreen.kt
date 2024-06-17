package br.com.fiap.localwebmail.screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import br.com.fiap.localwebmail.model.emails


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Seus e-mails") }, // Title next to the menu
                actions = { // Actions on the right side
                    IconButton(onClick = { navController.navigate("emailpage") }) {
                        Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("emailpage") }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Novo E-mail")
            }
        }
    )
    { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
//                .background(Color.DarkGray)
        ) {




            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Seus e-mails", style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )





                Column {
                    LazyColumn(contentPadding = innerPadding) {
                        items(emails) { email ->
                            EmailItem(email, navController)
                        }
                    }
                }
            }
        }
    }
}

