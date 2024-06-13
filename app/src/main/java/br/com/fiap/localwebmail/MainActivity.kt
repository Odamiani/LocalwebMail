package br.com.fiap.localwebmail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.material3.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider


import br.com.fiap.localwebmail.ui.theme.LocalwebMailTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocalwebMailTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EmailScreen()
                }
            }
        }
    }
}

@Composable
fun EmailScreen() {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Novo E-mail")
            }
        }
    ) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            items(emails) { email ->
                EmailItem(email)
            }
        }
    }
}

@Composable
fun EmailItem(email: Email) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
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

data class Email(
    val subject: String,
    val sender: String,
    val timestamp: String
)

val emails = listOf(
    Email("Reunião importante", "João Silva", "10:30"),
    Email("Promoção imperdível!", "Loja XYZ", "Ontem"),
)