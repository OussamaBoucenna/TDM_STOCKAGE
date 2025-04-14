package com.example.login


import android.graphics.drawable.Icon
import android.provider.CalendarContract.Colors
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lint.kotlin.metadata.Visibility



@Composable
fun SignInScreen(onLoginClick :()->Unit) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        // Fond avec la vague
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
        ) {
            val path = Path().apply {
                moveTo(0f, size.height * 0.28f)

// Première vague
                quadraticBezierTo(
                    size.width * 0.10f, size.height * 0.35f, // Point de contrôle
                    size.width * 0.25f, size.height * 0.25f  // Point final
                )

// Deuxième vague (un peu plus petite)
                quadraticBezierTo(
                    size.width * 0.45f, size.height * 0.15f, // Point de contrôle
                    size.width * 0.60f, size.height * 0.2f  // Point final
                )

// Troisième vague (encore plus petite)
                quadraticBezierTo(
                    size.width * 0.7f, size.height * 0.25f, // Point de contrôle
                    size.width *0.9f, size.height * 0.15f  // Point final
                )


// Fermer la forme
                lineTo(size.width, size.height *0.1f)
                lineTo(size.width, 0f)
                lineTo(0f,0f)


                moveTo(0f, size.height * 0.85f)
                quadraticBezierTo(
                    size.width * 0.15f, size.height * 0.80f, // Premier point de contrôle
                    size.width * 0.5f, size.height * 0.90f   // Premier point final
                )

// Deuxième vague
                quadraticBezierTo(
                    size.width * 0.75f, size.height * 0.95f, // Deuxième point de contrôle
                    size.width, size.height * 0.85f          // Deuxième point final
                )

                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }
            drawPath(path, color = Color(0xFF3B82F6)) // Bleu
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            // verticalArrangement = Arrangement.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.End ,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Button(
                    shape = RoundedCornerShape(4.dp), // Coins arrondis
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
                    onClick = { /* TODO: Action Sign In */ },
                    colors = ButtonDefaults.buttonColors(Color.White) // Bleu
                ) {
                    Text(text = "SIGN UP", color = Color(0xFF3B82F6))
                }
            }
            Spacer(modifier = Modifier
                .height(screenHeight * 0.2f)
                .width(20.dp)
                // .background(Color.Yellow)
            )
            Text(text = "Sign In To Continue",
                color = Color.Gray,
                fontSize = 20.sp,  // Change text size
                fontFamily = FontFamily.Serif,  // Change font family
                fontWeight = FontWeight.SemiBold,  // Make it bold
            )

            Row (
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp),
                horizontalArrangement =  Arrangement.SpaceBetween,

                ){
                IconButton(
                    onClick = {  }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.twitter_logo),
                        contentDescription = "Google Icon",
                        modifier = Modifier.size(30.dp)

                    )
                }
                IconButton(
                    onClick = {  }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.twitter_logo),
                        contentDescription = "Facebook Icon",
                        modifier = Modifier.size(30.dp)

                    )
                }

                IconButton(
                    onClick = {  }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.twitter_logo),
                        contentDescription = "Twitter Icon",
                        modifier = Modifier.size(30.dp)


                    )
                }
            }

            HorizontalLineWithText()
            Form(onLoginClick=onLoginClick)


        }

    }
}




@Composable
fun HorizontalLineWithText() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp), // Ajoute un padding autour de la ligne
        verticalAlignment = Alignment.CenterVertically // Aligne les éléments verticalement au centre
    ) {
        // Première ligne (à gauche du texte)
        HorizontalDivider(
            modifier = Modifier
                .weight(1f) // Prend tout l'espace disponible
                .padding(end = 8.dp), // Ajoute un espace entre la ligne et le texte
            color = Color.Gray, // Couleur de la ligne
            thickness = 1.dp // Épaisseur de la ligne
        )

        // Texte au milieu
        Text(
            text = "OR",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        // Deuxième ligne (à droite du texte)
        HorizontalDivider(
            modifier = Modifier
                .weight(1f) // Prend tout l'espace disponible
                .padding(start = 8.dp), // Ajoute un espace entre la ligne et le texte
            color = Color.Gray, // Couleur de la ligne
            thickness = 1.dp // Épaisseur de la ligne
        )
    }
}
@Composable
fun Form(onLoginClick: () -> Unit) {
    val context = LocalContext.current
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) } // État pour afficher/masquer le mot de passe
    var email by remember { mutableStateOf("") } // Utilisation d'une String au lieu de TextFieldValue
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp) // Ajoute du padding général
    ) {
        Text(text = "Email ID*" , color = Color.Gray)

        TextField(
            value = email,
            onValueChange = { newValue ->
                email = newValue
                errorMessage = if (isValidEmail(newValue)) null else "Adresse e-mail invalide"
            },
            placeholder = { Text("exemple@domaine.com") },
            keyboardOptions = KeyboardOptions( // Correction ici
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .background(Color(0xFF1E1E1E), shape = RoundedCornerShape(10.dp)) // Fond et coins arrondis
                .border(2.dp,Color.Gray , shape = RoundedCornerShape(10.dp)), // Bordure bleue
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White, // Couleur du texte actif
                unfocusedTextColor = Color.LightGray, // Texte inactif
                focusedContainerColor = Color.White, // Fond en mode focus
                unfocusedContainerColor = Color.White, // Fond par défaut
                cursorColor = Color(0xFF3B82F6), // Curseur bleu
                focusedIndicatorColor = Color.Transparent, // Supprimer la ligne par défaut
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Red // Couleur de la bordure en cas d'erreur
            ),
            isError = errorMessage != null
        )

        if (errorMessage != null) {
            Text(
                text = errorMessage!!,
                color = Color.Red,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Spacer(
            modifier = Modifier.height(12.dp)
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp), // Ajoute un padding autour de la ligne
            horizontalArrangement = Arrangement.SpaceBetween // Aligne les éléments verticalement au centre
        ) {

            Text(text = "Password*" , color = Color.Gray)
            Text(text = "Forgot Password?" , color = Color.Gray)
        }
        TextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Entrer votre mot de passe") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password, // Clavier pour mot de passe
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        if (passwordVisible) "Masquer le mot de passe" else "Afficher le mot de passe"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .background(Color(0xFF1E1E1E), shape = RoundedCornerShape(10.dp)) // Fond et coins arrondis
                .border(2.dp, Color.Gray, shape = RoundedCornerShape(10.dp)), // Bordure grise
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.LightGray,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                cursorColor = Color(0xFF3B82F6),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        Button(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF3B82F6)) ,// Bleu
            shape = RoundedCornerShape(4.dp), // Coins arrondis
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
            onClick = {
                if (email.trim() == "test@gmail.com" && password == "test") {
                    onLoginClick()
                }else{
                    Toast.makeText(context,"email or password incorrect",Toast.LENGTH_LONG).show()
                }
            }

        ) {
            Text(
                "SIGN IN",
                color = Color.White

            )
        }
    }


}

// Fonction pour valider une adresse e-mail
fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
