package br.com.fiap.localwebmail.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.localwebmail.model.Email

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailDetailsScreen(navController: NavController, email: Email) {
    Scaffold(
        topBar =
        {
            TopAppBar(


                title = { Text(email.subject) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    )
    { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {

                Column(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Card(
                        modifier = Modifier
                            //.height(35.dp)
                            .fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFC7DFFA)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                        border = BorderStroke(width = 3.dp, Color.Black)
                    ) {

                        Column(
                            modifier = Modifier
                                .padding(10.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "De: ${email.sender}",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    Card(
                        modifier = Modifier
                            //.height(35.dp)
                            .fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFC7DFFA)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                        border = BorderStroke(width = 3.dp, Color.Black)
                    ) {

                        Column(
                            modifier = Modifier
                                .padding(10.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = email.body, style = MaterialTheme.typography.bodyLarge)

                        }
                    }
                }



            }
        }
    }
}