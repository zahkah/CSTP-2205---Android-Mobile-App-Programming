package com.lab6_zga_15

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lab6_zga_15.entities_zga_15.WordListViewModel
import com.lab6_zga_15.ui.theme.DeleteRed
import com.lab6_zga_15.ui.theme.Lab6_zga_15Theme
import com.lab6_zga_15.ui.theme.LightGreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab6_zga_15Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    model: WordListViewModel = viewModel()
) {
    val wordList by model.wordList.collectAsState()
    val currentWord by model.currentWord.collectAsState()
    val editingIndex by model.editingIndex.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // 🔹 Display the grid (Items List)
        LazyVerticalGrid(
            columns = GridCells.Adaptive(128.dp),
            contentPadding = PaddingValues(10.dp),
        ) {
            items(wordList.size) { index ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .width(100.dp),
                    colors = CardDefaults.cardColors(containerColor = LightGreen),
                    onClick = { model.selectWordForEditing(index) }
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Item: ${wordList[index]}", color = MaterialTheme.colorScheme.onSurface)
                        IconButton(
                            onClick = { model.deleteWord(index) }
                        ) {
                            Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete", tint = DeleteRed)
                        }
                    }
                }
            }
        }

        // 🔹 Spacer for separation
        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 Add/Update Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            TextField(
                value = currentWord,
                onValueChange = { model.updateCurrentWord(it) },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Enter item") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface
                )
            )

            Spacer(Modifier.width(8.dp))

            Button(
                onClick = { model.addOrUpdateWord() },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    if (editingIndex != null) "Update" else "Add",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Lab6_zga_15Theme {
        MainScreen()
    }
}
