package com.microsoft.qrcode

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.microsoft.qrcode.compose.BottomNavigationBar
import com.microsoft.qrcode.ui.theme.QrcodeTheme

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QrcodeTheme {
                DashboardScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard") },
                navigationIcon = {
                    IconButton(onClick = { /* profile */ }) {
                        Icon(Icons.Default.AccountCircle, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = { /* notifications */ }) {
                        Icon(Icons.Default.Notifications, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigationBar()
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            //BalanceCard()

            TravelRouteCard()

            RecentDestinations()

            //MapPreview()
        }
    }
}
@Composable
fun BalanceCard() {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Available Balance", color = Color.Gray)
                Text(
                    "$25.50",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(onClick = { /* top up */ }) {
                    Icon(Icons.Default.Add, null)
                    Spacer(Modifier.width(4.dp))
                    Text("Top Up")
                }
            }

            Box(
                modifier = Modifier
                    .size(110.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                Color(0xFF6EC6FF),
                                Color(0xFF2196F3)
                            )
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
            )
        }
    }
}
@Composable
fun TravelRouteCard() {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                "Book QR Ticket",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            RouteField(
                label = "SOURCE STATION",
                value = "Central Station",
                icon = Icons.Default.RadioButtonChecked
            )

            RouteField(
                label = "DESTINATION STATION",
                value = "Enter destination",
                icon = Icons.Default.LocationOn,
                placeholder = true
            )

            Button(
                onClick = { /* book ticket */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Icon(Icons.Default.QrCode, null)
                Spacer(Modifier.width(8.dp))
                Text("Book QR Ticket", fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun RouteField(
    label: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    placeholder: Boolean = false
) {
    Column {
        Text(label, color = Color.Gray, fontSize = 12.sp)
        Spacer(Modifier.height(6.dp))
        OutlinedButton(
            onClick = { /* select */ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                value,
                modifier = Modifier.weight(1f),
                color = if (placeholder) Color.Gray else Color.Black
            )
            Icon(icon, null)
        }
    }
}
@Composable
fun RecentDestinations() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

        Text(
            "Recent Destinations",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        DestinationItem(
            title = "Airport Terminal 1",
            subtitle = "Last visited 2 days ago",
            icon = Icons.Default.History
        )

        DestinationItem(
            title = "Downtown Heights",
            subtitle = "Frequently visited",
            icon = Icons.Default.Home
        )
    }
}

@Composable
fun DestinationItem(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, null, tint = Color.Gray)
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.SemiBold)
                Text(subtitle, color = Color.Gray, fontSize = 12.sp)
            }
            TextButton(onClick = { /* select */ }) {
                Text("Select")
            }
        }
    }
}
@Composable
fun MapPreview() {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = { /* open map */ }) {
                Icon(Icons.Default.Map, null)
                Spacer(Modifier.width(6.dp))
                Text("View Full Map")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen (
    )
}