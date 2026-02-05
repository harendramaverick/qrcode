package com.microsoft.qrcode.compose

import android.app.Activity
import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.microsoft.qrcode.ProfileActivity

@Composable
fun BottomNavigationBar() {
    val context = LocalContext.current
    val activity = (context as? Activity)
    var selectedItem by remember { mutableStateOf(0) }

    NavigationBar {
        NavigationBarItem(
            selected = selectedItem == 0,
            onClick = { selectedItem = 0 },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") }
        )

        NavigationBarItem(
            selected = selectedItem == 1,
            onClick = { selectedItem = 1 },
            icon = { Icon(Icons.Default.ConfirmationNumber, contentDescription = "Tickets") },
            label = { Text("Tickets") }
        )

        NavigationBarItem(
            selected = selectedItem == 2,
            onClick = {
                selectedItem = 2
                val intent = Intent(context, ProfileActivity::class.java)
                context.startActivity(intent)
                activity?.finish()
            },
            icon = { Icon(Icons.Default.ReceiptLong, contentDescription = "History") },
            label = { Text("History") }
        )

        NavigationBarItem(
            selected = selectedItem == 3,
            onClick = {
                selectedItem = 3
                val intent = Intent(context, ProfileActivity::class.java)
                context.startActivity(intent)
                activity?.finish()
            },
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") }
        )
    }
}
