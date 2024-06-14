package br.com.fiap.localwebmail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue






import br.com.fiap.localwebmail.ui.theme.LocalwebMailTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocalwebMailTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "email"
                    ){
                        composable(route = "email") { EmailScreen(navController) }
                        composable(route = "emailpage") { EmailPage(navController) }
                        composable("emailsent") { EmailSentScreen(navController) }
                        composable("emaildetails/{emailId}") { backStackEntry ->
                            val emailId = backStackEntry.arguments?.getString("emailId")
                            val email = emails.find { it.id == emailId }
                            if (email != null) {
                                EmailDetailsScreen(navController, email)
                            } else {

                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun EmailScreen(navController: NavController) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("emailpage") }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Novo E-mail")
            }
        }
    ) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            items(emails) { email ->
                EmailItem(email, navController)
            }
        }
    }
}

@Composable
fun EmailItem(email: Email, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("emaildetails/${email.id}") }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = email.subject, style = MaterialTheme.typography.titleMedium)
            Text(text = email.sender, style = MaterialTheme.typography.titleMedium)
        }
        Text(text = email.timestamp, style = MaterialTheme.typography.titleLarge)
    }
    Divider()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailPage(navController: NavController){
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
            OutlinedTextField(
                value = recipient,
                onValueChange = { recipient = it },
                label = { Text("Para") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = subject,
                onValueChange = { subject = it },
                label = { Text("Assunto") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = body,
                onValueChange = { body = it },
                label = { Text("Escrever e-mail") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
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
@Composable
fun EmailSentScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate("email") {
            popUpTo("email") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("E-mail enviado com sucesso!")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailDetailsScreen(navController: NavController, email: Email) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(email.subject) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
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
            Text(text = "De: ${email.sender}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = email.body, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

data class Email(
    val id: String,
    val subject: String,
    val sender: String,
    val timestamp: String,
    val body: String
)

val emails = listOf(
    Email("1", "Reunião importante", "João Silva", "10:30", "Prezados, venho por meio deste e-mail convidá-los para uma reunião..."),
    Email("2", "Promoção imperdível!", "Loja XYZ", "Ontem", "Não perca a nossa promoção de aniversário...")
)