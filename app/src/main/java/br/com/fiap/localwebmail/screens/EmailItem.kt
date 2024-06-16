package br.com.fiap.localwebmail.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.localwebmail.model.Email

@Composable
fun EmailItem(email: Email, navController: NavController) {


    Card(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .clickable { navController.navigate("emaildetails/${email.id}") }
            .padding(
                horizontal = 8.dp,
                vertical = 3.dp
            ),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFC7DFFA)),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        border = BorderStroke(width = 3.dp, Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 17.dp,
                    horizontal = 10.dp
                )
        )
        {


            Image(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = email.subject, style = MaterialTheme.typography.titleMedium)
                Text(text = email.sender, style = MaterialTheme.typography.titleSmall)
            }
            Text(text = email.timestamp, style = MaterialTheme.typography.titleMedium)
        }
    }
}

