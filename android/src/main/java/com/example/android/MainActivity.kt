package com.example.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import domain.model.Character
import feature.details.DetailsScreen
import feature.home.HomeViewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                DetailsScreen(
                    character = Character(
                        id = 0,
                        name = "Rick Sanchez",
                        status = "Alive",
                        species = "Human",
                        gender = "Male",
                        imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                        origin = Character.Location(
                            name = "Earth",
                            url = "https://rickandmortyapi.com/api/location/1"
                        ),
                        location = Character.Location(
                            name = "Earth",
                            url = "https://rickandmortyapi.com/api/location/1"
                        ),
                        url = "https://rickandmortyapi.com/api/character/1"
                    ),
                    Modifier.fillMaxSize()
                )
            }
        }
    }
}