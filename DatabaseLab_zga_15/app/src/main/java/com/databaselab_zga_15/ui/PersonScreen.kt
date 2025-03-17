package com.databaselab_zga_15.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.databaselab_zga_15.model.Person
import com.databaselab_zga_15.viewmodel.PersonViewModel

@Composable
fun PersonScreen(viewModel: PersonViewModel) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var selectedPerson: Person? by remember { mutableStateOf(null) }

    val persons by viewModel.allPersons.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // **Header Banner with Name**
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFCC99), shape = RoundedCornerShape(8.dp))
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Database Lab: Your Name",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // **Person List**
        LazyColumn(
            modifier = Modifier.weight(1f) // Takes available space, pushing the button down
        ) {
            items(persons) { person ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .shadow(4.dp, shape = RoundedCornerShape(8.dp)),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFEDE7F6))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "${person.firstName} ${person.lastName}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            Text(text = person.email, fontSize = 14.sp)
                        }
                        IconButton(onClick = {
                            firstName = person.firstName
                            lastName = person.lastName
                            email = person.email
                            selectedPerson = person
                        }) {
                            Text("✏️") // Edit Icon
                        }
                        IconButton(onClick = { viewModel.delete(person) }) {
                            Text("🗑️") // Delete Icon
                        }
                    }
                }
            }
        }

        // **Input Fields**
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            TextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // **Add or Update Button at the Bottom**
        Button(
            onClick = {
                if (selectedPerson == null) {
                    viewModel.insert(Person(firstName = firstName, lastName = lastName, email = email))
                } else {
                    selectedPerson?.let {
                        viewModel.update(it.copy(firstName = firstName, lastName = lastName, email = email))
                    }
                    selectedPerson = null
                }
                firstName = ""
                lastName = ""
                email = ""
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(top = 8.dp)
        ) {
            Text(if (selectedPerson == null) "Add Person" else "Update Person", color = Color.White)
        }
    }
}
