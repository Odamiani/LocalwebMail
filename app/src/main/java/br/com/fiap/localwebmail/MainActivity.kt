package br.com.fiap.localwebmail


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.localwebmail.model.emails
import br.com.fiap.localwebmail.screens.EmailDetailsScreen
import br.com.fiap.localwebmail.screens.EmailPage
import br.com.fiap.localwebmail.screens.EmailScreen
import br.com.fiap.localwebmail.screens.EmailSentScreen
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
