package br.com.fiap.localwebmail.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailPage(navController: NavController) {
    var recipient by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Novo E-mail") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 30.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFC7DFFA)),
                border = BorderStroke(width = 2.dp, Color.Black)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {


                    OutlinedTextField(
                        maxLines = 1,
                        value = recipient,
                        onValueChange = { recipient = it },
                        label = { Text("Para:") },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "Para:")
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.Black,
                        )

                    )
                    OutlinedTextField(
                        maxLines = 1,
                        value = subject,
                        onValueChange = { subject = it },
                        label = { Text("Assunto") },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text(text = "Assunto") },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.Black,
                        )
                    )
                    OutlinedTextField(
                        shape = RoundedCornerShape(10.dp),
                        value = body,
                        onValueChange = { body = it },
                        label = { Text("Escrever e-mail") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        placeholder = { Text(text = "Escrever e-mail") },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.Black,
                        )
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Button(onClick = { }) {
                            Text("Anexar")
                        }
                        Button(onClick = { navController.navigate("emailsent") }) {
                            Text("Enviar")
                        }
                    }
                }
            }
        }
    }
}