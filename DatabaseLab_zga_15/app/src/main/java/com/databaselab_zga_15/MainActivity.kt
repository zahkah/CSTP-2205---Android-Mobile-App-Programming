package com.databaselab_zga_15

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.databaselab_zga_15.database.AppDatabase
import com.databaselab_zga_15.repository.PersonRepository
import com.databaselab_zga_15.ui.PersonScreen
import com.databaselab_zga_15.ui.theme.DatabaseLabTheme
import com.databaselab_zga_15.viewmodel.PersonViewModel
import com.databaselab_zga_15.viewmodel.ViewModelFactory

class MainActivity : ComponentActivity() {

    // Initialize database and repository before setting up ViewModel
    private val database by lazy { AppDatabase.getDatabase(this) }
    private val repository by lazy { PersonRepository(database.personDao()) }

    // Initialize ViewModel using ViewModelFactory
    private val viewModel: PersonViewModel by viewModels {
        ViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DatabaseLabTheme {
                PersonScreen(viewModel = viewModel)
            }
        }
    }
}
