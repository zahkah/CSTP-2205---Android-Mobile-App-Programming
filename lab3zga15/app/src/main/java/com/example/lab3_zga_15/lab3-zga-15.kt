package com.example.lab3_zga_15

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab3_zga_15.ui.theme.Lab3zga15Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab3zga15Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TemperatureConverter()
                }
            }
        }
    }
}

@Composable
fun TemperatureConverter() {
    var temperatureInput by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Temperature Converter",
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 26.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Input Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Input Field for Temperature
                OutlinedTextField(
                    value = temperatureInput,
                    onValueChange = { newValue ->
                        temperatureInput = newValue
                        errorMessage = "" // Clear error message when user types
                    },
                    label = { Text("Enter Temperature") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = errorMessage.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth()
                )

                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 5.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Buttons Row
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(
                onClick = {
                    val conversion = convertToFahrenheit(temperatureInput)
                    if (conversion != null) {
                        result = conversion
                    } else {
                        errorMessage = "Invalid input. Please enter a valid number."
                    }
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.weight(1f).padding(end = 8.dp)
            ) {
                Text("To Fahrenheit")
            }

            Button(
                onClick = {
                    val conversion = convertToCelsius(temperatureInput)
                    if (conversion != null) {
                        result = conversion
                    } else {
                        errorMessage = "Invalid input. Please enter a valid number."
                    }
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.weight(1f).padding(start = 8.dp)
            ) {
                Text("To Celsius")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Display Result
        if (result.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Text(
                    text = result,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(16.dp),
                )
            }
        }
    }
}

// Conversion Functions with Error Handling
fun convertToFahrenheit(celsius: String): String? {
    return try {
        val celsiusValue = celsius.toDouble()
        val fahrenheit = (celsiusValue * 9 / 5) + 32
        "$celsiusValue°C is ${"%.2f".format(fahrenheit)}°F"
    } catch (e: NumberFormatException) {
        null
    }
}

fun convertToCelsius(fahrenheit: String): String? {
    return try {
        val fahrenheitValue = fahrenheit.toDouble()
        val celsius = (fahrenheitValue - 32) * 5 / 9
        "$fahrenheitValue°F is ${"%.2f".format(celsius)}°C"
    } catch (e: NumberFormatException) {
        null
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTemperatureConverter() {
    Lab3zga15Theme {
        TemperatureConverter()
    }
}
