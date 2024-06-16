package br.com.fiap.localwebmail.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.localwebmail.model.Email

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