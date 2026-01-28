package com.microsoft.qrcode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.microsoft.qrcode.ui.theme.QrcodeTheme

class ActiveTicketActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QrcodeTheme() {
                ActiveTicketScreen(
                    onBack = { finish() }
                )
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActiveTicketScreen(onBack: () -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tickets") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = { Icon(Icons.Default.Home, null) },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.ConfirmationNumber, null) },
                    label = { Text("Tickets") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.ReceiptLong, null) },
                    label = { Text("History") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.Person, null) },
                    label = { Text("Profile") }
                )
            }
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            item {
                SectionTitle("CURRENT JOURNEY")
                ActiveTicketCard()
            }
            /*
            item {
                SectionTitle("PAST 4 JOURNEYS")
                PastJourneyList()
            }
            */
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { /* full history */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("View Full History")
                }
            }
        }
    }
}
@Composable
fun ActiveTicketCard() {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(Color(0xFF2ECC71), CircleShape)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    "ACTIVE",
                    color = Color(0xFF2ECC71),
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "Central Station to Airport T3",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "Today, 10:45 AM · Zone 1-3",
                        color = Color.Gray
                    )
                }

                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .background(
                            Color(0xFFFFE0CC),
                            RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.QrCode,
                        contentDescription = null,
                        tint = Color(0xFFFB8C00),
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            Button(
                onClick = { /* view ticket */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Icon(Icons.Default.QrCode, null)
                Spacer(Modifier.width(8.dp))
                Text("View Ticket", fontSize = 18.sp)
            }
        }
    }
}
@Composable
fun PastJourneyList() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

        PastJourneyItem(
            title = "North Gate to City Center",
            date = "Oct 24, 2023 · 08:30 AM",
            status = TicketStatus.USED
        )

        PastJourneyItem(
            title = "City Center to West Park",
            date = "Oct 23, 2023 · 06:15 PM",
            status = TicketStatus.USED
        )

        PastJourneyItem(
            title = "South Station to Downtown",
            date = "Oct 22, 2023 · 09:45 AM",
            status = TicketStatus.EXPIRED
        )

        PastJourneyItem(
            title = "West Park to North Gate",
            date = "Oct 21, 2023 · 05:20 PM",
            status = TicketStatus.USED
        )
    }
}
@Composable
fun PastJourneyItem(
    title: String,
    date: String,
    status: TicketStatus
) {
    val (icon, iconBg, chipBg, chipText) = when (status) {
        TicketStatus.USED -> Quad(
            Icons.Default.Check,
            Color(0xFFE9F5EE),
            Color(0xFFE9F5EE),
            Color(0xFF2E7D32)
        )
        TicketStatus.EXPIRED -> Quad(
            Icons.Default.Error,
            Color(0xFFFDECEA),
            Color(0xFFFDECEA),
            Color(0xFFC62828)
        )
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(iconBg, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, null, tint = chipText)
            }

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.SemiBold)
                Text(date, color = Color.Gray, fontSize = 12.sp)
            }

            Box(
                modifier = Modifier
                    .background(chipBg, RoundedCornerShape(50))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    status.name,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = chipText
                )
            }
        }
    }
}
@Composable
fun SectionTitle(text: String) {
    Text(
        text = text,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.Gray
    )
}

enum class TicketStatus {
    USED, EXPIRED
}

data class Quad<A, B, C, D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D
)


@Preview(showBackground = true)
@Composable
fun ActiveTicketScreenPreview() {
    ActiveTicketScreen(
        onBack = {  }
    )
}

