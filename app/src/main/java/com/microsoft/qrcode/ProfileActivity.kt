package com.microsoft.qrcode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.microsoft.qrcode.ui.theme.QrcodeTheme


class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QrcodeTheme() {
                ProfileScreen(
                    onBack = { finish() },
                    onLogout = { /* logout */ }
                )
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onBack: () -> Unit,
    onLogout: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(Modifier.height(24.dp))

            ProfileHeader()

            Spacer(Modifier.height(32.dp))

            ProfileMenuItem(
                icon = Icons.Default.Person,
                title = "Personal Information"
            )

            ProfileMenuItem(
                icon = Icons.Default.CreditCard,
                title = "Payment Methods"
            )

            ProfileMenuItem(
                icon = Icons.Default.Notifications,
                title = "Notification Settings"
            )

            ProfileMenuItem(
                icon = Icons.Default.Help,
                title = "Help & Support"
            )

            Spacer(Modifier.height(40.dp))

            LogoutButton(onLogout)

            Spacer(Modifier.height(24.dp))

            Text(
                "Metro Ticketing v2.4.0",
                fontSize = 12.sp,
                color = Color.Gray
            )

            Spacer(Modifier.height(16.dp))
        }
    }
}
@Composable
fun ProfileHeader() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Box(
            modifier = Modifier
                .size(110.dp)
                .background(Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_avatar),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
        }

        Spacer(Modifier.height(16.dp))

        Text(
            "Alex Johnson",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            "+1 555-0123",
            color = Color(0xFF4F7FFF),
            fontSize = 16.sp
        )
    }
}
@Composable
fun ProfileMenuItem(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(Color(0xFFEAF1FF), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = Color(0xFF2F6BFF)
                )
            }

            Spacer(Modifier.width(16.dp))

            Text(
                title,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )

            Icon(
                Icons.Default.ChevronRight,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}
@Composable
fun LogoutButton(onLogout: () -> Unit) {
    OutlinedButton(
        onClick = onLogout,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color(0xFFD32F2F)
        ),
        border = BorderStroke(1.dp, Color(0xFFF5C6C6)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Icon(Icons.Default.Logout, null)
        Spacer(Modifier.width(8.dp))
        Text(
            "Logout",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
